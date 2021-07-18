package com.spitzer.darkroastedbeans.ui.coffeestyle

import com.spitzer.darkroastedbeans.core.BaseViewModel

class CoffeeStyleFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() = setToolbarConfiguration(
        "Brew with Lex",
        "Select your style",
        true
    )
}