package com.spitzer.darkroastedbeans.ui.coffeeextra

import com.spitzer.darkroastedbeans.core.BaseViewModel
import com.spitzer.darkroastedbeans.core.Event
import com.spitzer.darkroastedbeans.navigation.NavigationCommand

class CoffeeExtrasFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() = setToolbarConfiguration(
        FRAGMENT_TITLE,
        FRAGMENT_MAIN_TEXT,
        IS_BACK_ALLOWED
    )

    fun onHeaderClick(headerId: String) {
        // TODO handle extras without subselections
    }

    fun onExtrasClick(headerId: String, extraId: String) {
        if (headerId.isNotEmpty() && extraId.isNotEmpty()) {
            // checking if the list of extras is null
            if (coffeeSelectionModel.value!!.extras == null) {
                coffeeSelectionModel.value!!.extras = arrayListOf()
            }
            // preventing adding more than on extras from the same type
            val filteredExtras = coffeeSelectionModel.value!!.extras?.filter {
                it.first != headerId
            } as ArrayList<Pair<String, String>>
            filteredExtras.add(Pair(headerId, extraId))
            coffeeSelectionModel.value!!.extras = filteredExtras
        } else {
            // if extraId is blank, we have to delete all extras of that type
            if (extraId.isEmpty()) {
                val filteredExtras = coffeeSelectionModel.value!!.extras?.filter {
                    it.first != headerId
                } as ArrayList<Pair<String, String>>
                coffeeSelectionModel.value!!.extras = filteredExtras
            }
        }
    }

    fun navigateToCoffeeSelection() {
        val action = CoffeeExtrasFragmentDirections
            .actionCoffeeExtrasFragmentToCoffeeSelectionFragment(
                coffeeSelectionModel.value!!
            )

        _navigation.value = Event(NavigationCommand.To(action))
    }

    companion object {
        const val FRAGMENT_TITLE = "Brew with Lex"
        const val FRAGMENT_MAIN_TEXT = "Select your extras"
        const val IS_BACK_ALLOWED = true
    }
}
