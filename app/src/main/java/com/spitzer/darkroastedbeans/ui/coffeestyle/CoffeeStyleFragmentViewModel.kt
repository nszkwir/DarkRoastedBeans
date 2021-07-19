package com.spitzer.darkroastedbeans.ui.coffeestyle

import com.spitzer.darkroastedbeans.core.BaseViewModel
import com.spitzer.darkroastedbeans.core.Event
import com.spitzer.darkroastedbeans.navigation.NavigationCommand

class CoffeeStyleFragmentViewModel : BaseViewModel() {

    override fun configureToolbar() = setToolbarConfiguration(
        FRAGMENT_TITLE,
        FRAGMENT_MAIN_TEXT,
        IS_BACK_ALLOWED
    )

    fun onHeaderClick(headerId: String) {
        if (headerId.isNotEmpty()) {
            coffeeSelectionModel.value!!.styleId = headerId

            val action = CoffeeStyleFragmentDirections
                .actionCoffeeStyleFragmentToCoffeeSizeFragment(
                    coffeeSelectionModel.value!!
                )

            _navigation.value = Event(NavigationCommand.To(action))
        }
    }

    fun onExtrasClick(headerId: String, extraId: String) {
        // TODO handle coffee styles with extras if possible
    }

    companion object {
        const val FRAGMENT_TITLE = "Brew with Lex"
        const val FRAGMENT_MAIN_TEXT = "Select your style"
        const val IS_BACK_ALLOWED = true
    }
}
