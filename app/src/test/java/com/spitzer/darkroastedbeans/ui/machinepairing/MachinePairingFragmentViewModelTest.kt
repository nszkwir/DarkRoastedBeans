package com.spitzer.darkroastedbeans.ui.machinepairing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.spitzer.darkroastedbeans.core.Event
import com.spitzer.darkroastedbeans.core.ToolbarConfiguration
import com.spitzer.darkroastedbeans.repositories.coffeemachine.CoffeeMachineRepository
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.*
import com.spitzer.darkroastedbeans.ui.machinepairing.MachinePairingFragmentViewModel.Companion.COFFEE_MACHINE_ID
import com.spitzer.network.ResultData
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MachinePairingFragmentViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CoffeeMachineRepository

    @Mock
    private lateinit var observer: Observer<Event<CoffeeMachineConfiguration>>
    @Mock
    private lateinit var toolbarObserver: Observer<Event<ToolbarConfiguration>>

    private lateinit var viewModel: MachinePairingFragmentViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel =
            MachinePairingFragmentViewModel(TestCoroutineScope().coroutineContext, repository)
    }

    @Test
    fun toolbarSetup() = runBlockingTest {
        val title = "Dark Roasted Beans"
        val mainText = "Tab the machine to start"
        viewModel.setToolbarConfiguration(title, mainText, false)
        assertNotNull(viewModel.toolbarConfiguration)
        viewModel.toolbarConfiguration.observeForever(toolbarObserver)
        assertTrue(viewModel.toolbarConfiguration.hasObservers())
        verify(toolbarObserver).onChanged(viewModel.toolbarConfiguration.value!!)
        assertEquals(viewModel.toolbarConfiguration.value!!.peekContent().title, title)
        assertEquals(viewModel.toolbarConfiguration.value!!.peekContent().mainText, mainText)
        assertEquals(viewModel.toolbarConfiguration.value!!.peekContent().showBackIcon, false)
    }

    @Test
    fun gettingSuccess_butNoData() = runBlockingTest {
        `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)).thenReturn(null)
        viewModel.getCoffeeMachineConfiguration()
        assertNotNull(viewModel.coffeeMachineConfiguration)
        viewModel.coffeeMachineConfiguration.observeForever(observer)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
        verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        verifyNoInteractions(observer)
    }

    @Test
    fun gettingSuccess_emptyModel() = runBlockingTest {
        `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
            .thenReturn(ResultData.Success(CoffeeMachineConfiguration("test")))
        viewModel.getCoffeeMachineConfiguration()
        assertNotNull(viewModel.coffeeMachineConfiguration)
        viewModel.coffeeMachineConfiguration.observeForever(observer)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
        verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        assertEquals(viewModel.coffeeMachineConfiguration.value!!.peekContent().id, "test")
        assertEquals(
            viewModel.coffeeMachineConfiguration.value!!.peekContent().types,
            arrayListOf<CoffeeType>()
        )
        assertEquals(
            viewModel.coffeeMachineConfiguration.value!!.peekContent().sizes,
            arrayListOf<CoffeeType>()
        )
        assertEquals(
            viewModel.coffeeMachineConfiguration.value!!.peekContent().extras,
            arrayListOf<CoffeeExtra>()
        )
        verify(observer).onChanged(viewModel.coffeeMachineConfiguration.value)
    }

    @Test
    fun gettingSuccess_notEmptyModel() = runBlockingTest {
        val types = arrayListOf<CoffeeType>(
            CoffeeType("1", "1"),
            CoffeeType("2", "2"),
        )
        val sizes = arrayListOf<CoffeeSize>(
            CoffeeSize("1", "1"),
            CoffeeSize("2", "2"),
        )
        val extras = arrayListOf<CoffeeExtra>(
            CoffeeExtra("1", "1", arrayListOf(CoffeeExtraSubselection("1", "1"))),
            CoffeeExtra("2", "2"),
        )

        `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
            .thenReturn(
                ResultData.Success(
                    CoffeeMachineConfiguration(
                        "test", types, sizes, extras
                    )
                )
            )
        viewModel.getCoffeeMachineConfiguration()
        assertNotNull(viewModel.coffeeMachineConfiguration)
        viewModel.coffeeMachineConfiguration.observeForever(observer)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
        verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        assertEquals(viewModel.coffeeMachineConfiguration.value!!.peekContent().id, "test")
        assertEquals(viewModel.coffeeMachineConfiguration.value!!.peekContent().types!!.size, 2)
        assertEquals(viewModel.coffeeMachineConfiguration.value!!.peekContent().sizes!!.size, 2)
        assertEquals(viewModel.coffeeMachineConfiguration.value!!.peekContent().extras!!.size, 2)
        assertEquals(
            viewModel.coffeeMachineConfiguration.value!!.peekContent().extras!!.first().subselections!!.size,
            1
        )
        verify(observer).onChanged(viewModel.coffeeMachineConfiguration.value)
    }

    @Test
    fun gettingError() = runBlockingTest {
        `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
            .thenReturn(ResultData.Error(Exception("something went wrong")))
        viewModel.getCoffeeMachineConfiguration()
        assertNotNull(viewModel.coffeeMachineConfiguration)
        viewModel.coffeeMachineConfiguration.observeForever(observer)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
        verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        verifyNoInteractions(observer)
    }
}