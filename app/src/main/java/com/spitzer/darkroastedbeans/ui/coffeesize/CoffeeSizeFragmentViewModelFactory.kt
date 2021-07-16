package com.spitzer.darkroastedbeans.ui.coffeesize

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoffeeSizeFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelo: Class<T>): T {
        return CoffeeSizeFragmentViewModel() as T
    }
}
