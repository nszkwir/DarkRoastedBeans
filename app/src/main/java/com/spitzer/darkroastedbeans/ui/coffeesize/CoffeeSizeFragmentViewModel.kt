package com.spitzer.darkroastedbeans.ui.coffeesize

import com.spitzer.darkroastedbeans.core.BaseViewModel

class CoffeeSizeFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() = setToolbarConfiguration(
        "Brew with Lex",
        "Select your size",
        true
    )
}