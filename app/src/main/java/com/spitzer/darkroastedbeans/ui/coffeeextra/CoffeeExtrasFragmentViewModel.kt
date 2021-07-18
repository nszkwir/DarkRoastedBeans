package com.spitzer.darkroastedbeans.ui.coffeeextra

import com.spitzer.darkroastedbeans.core.BaseViewModel

class CoffeeExtrasFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() = setToolbarConfiguration(
        "Brew with Lex",
        "Select your extras",
        true
    )
}
