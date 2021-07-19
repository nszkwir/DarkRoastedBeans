package com.spitzer.darkroastedbeans.ui.coffeeextra

import com.spitzer.darkroastedbeans.core.BaseViewModel

class CoffeeExtrasFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() = setToolbarConfiguration(
        FRAGMENT_TITLE,
        FRAGMENT_MAIN_TEXT,
        IS_BACK_ALLOWED
    )

    companion object {
        const val FRAGMENT_TITLE = "Brew with Lex"
        const val FRAGMENT_MAIN_TEXT = "Select your extras"
        const val IS_BACK_ALLOWED = true
    }
}
