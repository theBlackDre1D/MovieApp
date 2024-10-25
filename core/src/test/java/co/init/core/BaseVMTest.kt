package co.init.core

import co.init.core.base.BaseVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseVMTest {

    class TestVM : BaseVM() {
        suspend fun triggerError(error: String?) {
            _error.emit(error)
        }
    }

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: TestVM

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TestVM()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test error flow emits correct values`() = runBlocking {
        val emittedErrors = mutableListOf<String?>()
        val collectJob = launch(Dispatchers.Unconfined) {
            viewModel.error.toList(emittedErrors)
        }
        viewModel.triggerError("Error 1")
        viewModel.triggerError("Error 2")
        viewModel.triggerError(null)

        assertEquals(listOf("Error 1", "Error 2", null), emittedErrors)

        collectJob.cancel()
    }

    @Test
    fun `test if error is shared flow`() {
        assertTrue(viewModel.error is SharedFlow)
    }
}