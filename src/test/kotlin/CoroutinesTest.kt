/** Copyright © 2021 Robert Bosch GmbH. All rights reserved. */
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class CoroutinesTest {

  @Test
  fun `flows are combined instantly`() = runBlockingTest {
    val flow1 = MutableStateFlow("A")
    val flow2 = MutableStateFlow(0)
    var result = ""
    var expected = flow1.value + flow2.value

    val job = launch {
      combine(flow1, flow2) { v1, v2 ->
        v1 + v2
      }.collect {
        result += it
      }
    }

    advanceUntilIdle()

    repeat(3) {
      flow1.value = flow1.value[0].inc().toString()
      expected += flow1.value + flow2.value
      flow2.value = flow2.value.inc()
      expected += flow1.value + flow2.value
    }

    advanceUntilIdle()

    println("Expecting $result to match $expected")
    assertEquals(expected, result)
    job.cancel()
  }
}
