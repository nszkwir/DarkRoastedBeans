package com.spitzer.darkroastedbeans.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.spitzer.darkroastedbeans.MainActivity
import com.spitzer.darkroastedbeans.R

class CoffeeExtrasFragment : Fragment() {

    companion object {
        fun newInstance() = CoffeeExtrasFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        (activity as MainActivity).showProgressBar()
        (activity as MainActivity).setupCustomToolbar(
            "Dark Roasted Beans",
            "Tab the machine to start",
            false
        )
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
//        (activity as MainActivity).showProgressBar()
//        (activity as MainActivity).setupCustomToolbar(
//            "Dark Roasted Beans",
//            "Tab the machine to start",
//            false
//        )
//    }

}