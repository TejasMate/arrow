package arrow.fx.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

// The normal dispatcher with 'runTest' does some magic
// which doesn't go well with 'parZip', 'parMap', and 'raceN'
fun runTestUsingDefaultDispatcher(
  timeout: Duration = 40.seconds,
  testBody: suspend TestScope.() -> Unit
): TestResult = runTest(timeout = timeout) {
  withContext(Dispatchers.Default) {
    testBody()
  }
}
