package com.spitzer.darkroastedbeans.repositories.coffeemachine

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.*
import com.spitzer.darkroastedbeans.ui.machinepairing.MachinePairingFragmentViewModel.Companion.COFFEE_MACHINE_ID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CoffeeMachineRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var service: CoffeeMachineService

    private lateinit var repository: CoffeeMachineRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository =
            CoffeeMachineRepositoryImpl(service, TestCoroutineDispatcher())
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
            Mockito.`when`(service.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
                .thenReturn(
                    Response.success(
                        200, CoffeeMachineConfiguration(
                            "test", types, sizes, extras
                        )
                    )
                )

            repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)

            assertEquals(
                repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID).getResponseCode(), 200
            )

        }
    }


    @Test
    fun `error while fetching Coffee Machine Configuration`() {

        runBlockingTest {
            Mockito.`when`(service.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID))
                .thenReturn(
                    Response.error(
                        500,
                        ResponseBody.create(MediaType.get("application/json"), "{}")
                    )
                )

            repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)

            assertEquals(
                repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID).getErrorCode(),
                500
            )

        }
    }
}