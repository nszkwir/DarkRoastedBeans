package com.spitzer.darkroastedbeans.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spitzer.darkroastedbeans.model.CoffeeSelectionModel

abstract class BaseViewModel : ViewModel() {

    private val _coffeeSelectionModel = MutableLiveData<CoffeeSelectionModel>()
    val coffeeSelectionModel: LiveData<CoffeeSelectionModel> = _coffeeSelectionModel

    private val _toolbarConfiguration = MutableLiveData<Event<ToolbarConfiguration>>()
    val toolbarConfiguration: LiveData<Event<ToolbarConfiguration>> = _toolbarConfiguration

    abstract fun configureToolbar()

    fun setToolbarConfiguration(title: String, mainText: String, showBackIcon: Boolean) {
        _toolbarConfiguration.value = Event(
            ToolbarConfiguration(title, mainText, showBackIcon)
        )
    }

    fun setCoffeeSelectionModel(model: CoffeeSelectionModel) {
        _coffeeSelectionModel.value = model
    }
}
