package com.spitzer.darkroastedbeans.ui.machinepairing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.spitzer.darkroastedbeans.core.Event
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

    private lateinit var viewModel: MachinePairingFragmentViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel =
            MachinePairingFragmentViewModel(TestCoroutineScope().coroutineContext, repository)
    }

    @Test
    fun `successful fetch of Coffee Machine Configuration but getting null data`() {

        runBlockingTest {
            `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)).thenReturn(null)
            viewModel.getCoffeeMachineConfiguration()
            verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        }

        viewModel.coffeeMachineConfiguration.observeForever(observer)

        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
        assertNotNull(viewModel.coffeeMachineConfiguration)

        verifyNoInteractions(observer)
    }

    @Test
    fun `successful fetch of Coffee Machine Configuration but getting empty configuration`() {
        runBlockingTest {
            `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
                .thenReturn(ResultData.Success(CoffeeMachineConfiguration("test")))
            viewModel.getCoffeeMachineConfiguration()
            verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        }

        viewModel.coffeeMachineConfiguration.observeForever(observer)

        assertNotNull(viewModel.coffeeMachineConfiguration)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
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
    fun `successful fetch of Coffee Machine Configuration`() {
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

        runBlockingTest {
            `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
                .thenReturn(
                    ResultData.Success(
                        CoffeeMachineConfiguration(
                            "test", types, sizes, extras
                        )
                    )
                )
            viewModel.getCoffeeMachineConfiguration()
            verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        }

        viewModel.coffeeMachineConfiguration.observeForever(observer)

        assertNotNull(viewModel.coffeeMachineConfiguration)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
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
    fun `getting Error while fetching Coffee Machine Configuration`() {
        runBlockingTest {
            `when`(repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
                .thenReturn(ResultData.Error(Exception("something went wrong")))
            viewModel.getCoffeeMachineConfiguration()
            verify(repository).getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)
        }

        viewModel.coffeeMachineConfiguration.observeForever(observer)

        assertNotNull(viewModel.coffeeMachineConfiguration)
        assertTrue(viewModel.coffeeMachineConfiguration.hasObservers())
        verifyNoInteractions(observer)
    }
}