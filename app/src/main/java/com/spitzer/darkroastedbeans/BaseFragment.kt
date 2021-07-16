package com.spitzer.darkroastedbeans

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun hideProgress() = (activity as MainActivity).hideProgressBar()
    fun showProgress() = (activity as MainActivity).showProgressBar()
    fun showSnackBar(message: String) = (activity as MainActivity).showSnackbar(message)

    fun setupCustomToolbar(title: String, mainText: String, shouldShowBackArrow: Boolean) {
        (activity as MainActivity).setupCustomToolbar(
            title,
            mainText,
            shouldShowBackArrow
        )
    }
}
