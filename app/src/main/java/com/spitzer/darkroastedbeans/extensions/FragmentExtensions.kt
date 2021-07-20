package com.spitzer.darkroastedbeans.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.core.BaseViewModel
import com.spitzer.darkroastedbeans.core.Event
import com.spitzer.darkroastedbeans.core.ToolbarConfiguration
import com.spitzer.darkroastedbeans.ui.MainActivity

fun Fragment.showSnackbar(text: String, duration: Int) {
    activity?.let {
        val snackbar = Snackbar.make(
            it.findViewById<View>(R.id.coordinator),
            text,
            duration
        )
        val view = snackbar.view
        view.setBackgroundColor(resources.getColor(R.color.snackbarColor))
        snackbar.show()
    }
}

fun Fragment.setupSnackbar(owner: LifecycleOwner, event: LiveData<Event<Int>>, duration: Int) {
    event.observe(owner, { event ->
        event.getContentIfNotHandled()?.let { res ->
            context?.let { showSnackbar(it.getString(res), duration) }
        }
    })
}

fun BaseFragment.setupToolbar(owner: LifecycleOwner, viewModel: BaseViewModel) {
    viewModel.toolbarConfiguration.observe(owner, { event ->
        event.getContentIfNotHandled()?.let { configuration ->
            (activity as MainActivity).let {
                it.setupCustomToolbar(
                    getString(configuration.title),
                    getString(configuration.mainText),
                    configuration.showBackIcon
                )
            }
        }
    })
    viewModel.configureToolbar()
}