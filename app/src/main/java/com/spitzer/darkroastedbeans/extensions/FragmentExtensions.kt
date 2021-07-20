package com.spitzer.darkroastedbeans.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.core.Event

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
    event.observe(owner, Observer { event ->
        event.getContentIfNotHandled()?.let { res ->
            context?.let { showSnackbar(it.getString(res), duration) }
        }
    })
}