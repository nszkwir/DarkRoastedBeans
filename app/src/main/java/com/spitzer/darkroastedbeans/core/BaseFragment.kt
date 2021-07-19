package com.spitzer.darkroastedbeans.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spitzer.darkroastedbeans.navigation.NavigationCommand
import com.spitzer.darkroastedbeans.ui.MainActivity

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation(getViewModel())
        observeToolbarConfiguration(getViewModel())
    }

    abstract fun getViewModel(): BaseViewModel

    fun hideProgress() = (activity as MainActivity).hideProgressBar()
    fun showProgress() = (activity as MainActivity).showProgressBar()
    fun showSnackBar(message: String) = (activity as MainActivity).showSnackbar(message)

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> {
                        findNavController().navigate(command.directions)
                    }
                    is NavigationCommand.Back -> {
                        findNavController().navigateUp()
                    }
                }
            }
        })
    }

    private fun observeToolbarConfiguration(viewModel: BaseViewModel) {
        viewModel.toolbarConfiguration.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { toolbarConfiguration ->
                setupCustomToolbar(
                    toolbarConfiguration.title,
                    toolbarConfiguration.mainText,
                    toolbarConfiguration.showBackIcon
                )
            }
        })
    }

    private fun setupCustomToolbar(title: String, mainText: String, shouldShowBackArrow: Boolean) {
        (activity as MainActivity).setupCustomToolbar(
            title,
            mainText,
            shouldShowBackArrow
        )
    }

    override fun onResume() {
        super.onResume()
        getViewModel().configureToolbar()
    }
}
