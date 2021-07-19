package com.spitzer.darkroastedbeans.ui.coffeeselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoffeeSelectionFragmentViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelo: Class<T>): T {
        return CoffeeSelectionFragmentViewModel() as T
    }
}
