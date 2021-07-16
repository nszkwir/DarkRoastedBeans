package com.spitzer.darkroastedbeans.mainfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spitzer.darkroastedbeans.repositories.CoffeeMachineRepositoryImpl
import kotlinx.coroutines.Dispatchers

class MainFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelo: Class<T>): T {
        return MainFragmentViewModel(
            Dispatchers.Main,
            CoffeeMachineRepositoryImpl()
        ) as T
    }
}