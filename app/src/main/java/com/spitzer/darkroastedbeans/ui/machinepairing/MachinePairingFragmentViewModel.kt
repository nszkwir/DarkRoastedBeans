package com.spitzer.darkroastedbeans.ui.machinepairing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.core.BaseViewModel
import com.spitzer.darkroastedbeans.core.Event
import com.spitzer.darkroastedbeans.navigation.NavigationCommand
import com.spitzer.darkroastedbeans.repositories.coffeemachine.CoffeeMachineRepository
import com.spitzer.darkroastedbeans.repositories.coffeemachine.CoffeeMachineRepositoryImpl
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeMachineConfiguration
import com.spitzer.network.ResultData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MachinePairingFragmentViewModel(
    override val coroutineContext: CoroutineContext,
    private val repository: CoffeeMachineRepository = CoffeeMachineRepositoryImpl()
) : BaseViewModel(), CoroutineScope {

    private var _coffeeMachineConfiguration = MutableLiveData<Event<CoffeeMachineConfiguration>>()
    var coffeeMachineConfiguration: LiveData<Event<CoffeeMachineConfiguration>> =
        _coffeeMachineConfiguration

    override fun configureToolbar() = setToolbarConfiguration(
        FRAGMENT_TITLE,
        FRAGMENT_MAIN_TEXT,
        IS_BACK_ALLOWED
    )

    fun navigateForward() {
        val action = MachinePairingFragmentDirections
            .actionMachinePairingFragmentToCoffeeStyleFragment(
                coffeeSelectionModel.value!!
            )
        _navigation.value = Event(NavigationCommand.To(action))
    }
    fun getCoffeeMachineConfiguration() {
        _loading.value = Event(true)
        this.requestCoffeeMachineConfiguration()
    }

    private fun requestCoffeeMachineConfiguration() = launch {
        when (val result = repository.getCoffeeMachineConfiguration(COFFEE_MACHINE_ID)) {

            is ResultData.Success -> {
                _loading.value = Event(false)
                if (result.data != null) {
                    _coffeeMachineConfiguration.value = Event(result.data!!)
                } else {
                    _snackbarError.value = Event(R.string.snackbar_could_not_fetch)
                }
            }
            is ResultData.Error -> {
                _loading.value = Event(false)
                if (result.isNetworkError()) {
                    _snackbarError.value = Event(R.string.snackbar_network_error)
                } else {
                    _snackbarError.value = Event(R.string.snackbar_error)
                }
            }
        }
    }

    companion object {
        const val FRAGMENT_TITLE = "Dark Roasted Beans"
        const val FRAGMENT_MAIN_TEXT = "Tab the machine to start"
        const val IS_BACK_ALLOWED = false
        const val COFFEE_MACHINE_ID = "60ba1ab72e35f2d9c786c610"
    }
}
