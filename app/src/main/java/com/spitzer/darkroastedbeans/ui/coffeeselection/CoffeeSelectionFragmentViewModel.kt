package com.spitzer.darkroastedbeans.ui.coffeeselection

import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.core.BaseViewModel
import com.spitzer.darkroastedbeans.core.Event
import com.spitzer.darkroastedbeans.core.ToolbarConfiguration
import com.spitzer.darkroastedbeans.ui.coffeestyle.CoffeeStyleFragmentViewModel

class CoffeeSelectionFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() {
        _toolbarConfiguration.value = Event(
            ToolbarConfiguration(
                FRAGMENT_TITLE,
                FRAGMENT_MAIN_TEXT,
                IS_BACK_ALLOWED
            )
        )
    }

    fun getStyleId() = coffeeSelectionModel.value!!.styleId
    fun getSizeId() = coffeeSelectionModel.value!!.sizeId

    fun getExtrasDetail(): String {
        if (coffeeSelectionModel.value!!.extras != null && coffeeSelectionModel.value!!.extras!!.isNotEmpty()) {
            var extrasString = ""
            coffeeSelectionModel.value!!.extras!!.forEach {
                extrasString = extrasString + it.first + "-" + it.second + "\n"
            }
            return extrasString
        }
        return ""
    }

    companion object {
        const val FRAGMENT_TITLE = R.string.fragment_generic_title
        const val FRAGMENT_MAIN_TEXT = R.string.fragment_coffee_selection_main_text
        const val IS_BACK_ALLOWED = true
    }
}
