package com.spitzer.darkroastedbeans.ui.coffeestyle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoffeeStyleFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelo: Class<T>): T {
        return CoffeeStyleFragmentViewModel() as T
    }
}
