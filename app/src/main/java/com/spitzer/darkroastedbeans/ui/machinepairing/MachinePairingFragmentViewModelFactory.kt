package com.spitzer.darkroastedbeans.ui.machinepairing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spitzer.darkroastedbeans.repositories.coffeemachine.CoffeeMachineRepositoryImpl
import kotlinx.coroutines.Dispatchers

class MachinePairingFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelo: Class<T>): T {
        return MachinePairingFragmentViewModel(
            Dispatchers.Main,
            CoffeeMachineRepositoryImpl()
        ) as T
    }
}
