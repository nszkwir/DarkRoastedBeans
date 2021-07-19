package com.spitzer.darkroastedbeans.ui.coffeeselection

import com.spitzer.darkroastedbeans.core.BaseViewModel

class CoffeeSelectionFragmentViewModel : BaseViewModel() {
    override fun configureToolbar() = setToolbarConfiguration(
        FRAGMENT_TITLE,
        FRAGMENT_MAIN_TEXT,
        IS_BACK_ALLOWED
    )

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
        const val FRAGMENT_TITLE = "Brew with Lex"
        const val FRAGMENT_MAIN_TEXT = "Your Selection!"
        const val IS_BACK_ALLOWED = true
    }
}
