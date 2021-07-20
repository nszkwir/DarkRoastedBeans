package com.spitzer.darkroastedbeans.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spitzer.darkroastedbeans.model.CoffeeSelectionModel
import com.spitzer.darkroastedbeans.navigation.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    protected val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    protected val _snackbarError = MutableLiveData<Event<Int>>()
    val snackbarError : LiveData<Event<Int>> = _snackbarError

    protected val _loading = MutableLiveData<Event<Boolean>>()
    val loading : LiveData<Event<Boolean>> = _loading

    private val _coffeeSelectionModel = MutableLiveData<CoffeeSelectionModel>()
    val coffeeSelectionModel: LiveData<CoffeeSelectionModel> = _coffeeSelectionModel

    protected val _toolbarConfiguration = MutableLiveData<Event<ToolbarConfiguration>>()
    val toolbarConfiguration: LiveData<Event<ToolbarConfiguration>> = _toolbarConfiguration

    abstract fun configureToolbar()

    fun setCoffeeSelectionModel(model: CoffeeSelectionModel) {
        _coffeeSelectionModel.value = model
    }
}
