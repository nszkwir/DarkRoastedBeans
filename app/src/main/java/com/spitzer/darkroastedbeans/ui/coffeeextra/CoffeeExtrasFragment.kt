package com.spitzer.darkroastedbeans.ui.coffeeextra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.spitzer.darkroastedbeans.ui.MainActivity
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.ui.machinepairing.MachinePairingFragmentViewModel

class CoffeeExtrasFragment : Fragment() {

    companion object {
        fun newInstance() = CoffeeExtrasFragment()
    }

    private lateinit var fragmentViewModel: MachinePairingFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.machine_pairing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewModel = ViewModelProvider(this).get(MachinePairingFragmentViewModel::class.java)
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