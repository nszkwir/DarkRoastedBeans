package com.spitzer.darkroastedbeans.ui.coffeeextra

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoffeeExtrasFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelo: Class<T>): T {
        return CoffeeExtrasFragmentViewModel() as T
    }
}
