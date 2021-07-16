package com.spitzer.darkroastedbeans.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spitzer.darkroastedbeans.navigation.NavigationCommand
import com.spitzer.darkroastedbeans.navigation.ToolbarConfiguration

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private val _toolbarConfiguration = MutableLiveData<Event<ToolbarConfiguration>>()
    val toolbarConfiguration: LiveData<Event<ToolbarConfiguration>> = _toolbarConfiguration

    fun setToolbarConfiguration(title: String, mainText: String, showBackIcon: Boolean) {
        _toolbarConfiguration.value = Event(
            ToolbarConfiguration(title, mainText, showBackIcon)
        )
    }
}