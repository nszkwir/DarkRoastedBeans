package com.spitzer.darkroastedbeans.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.BaseFragment
import com.spitzer.darkroastedbeans.databinding.MainFragmentBinding

class MainFragment : BaseFragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainFragmentViewModelFactory()
        ).get(MainFragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        setupView()
//        defineObservables()
        return binding.root
    }

    fun setupView() {
        setupCustomToolbar(
            getString(R.string.main_fragment_title),
            getString(R.string.main_fragment_main_text),
            false
        )
        binding.backgroundImage.setOnClickListener {
            //showSnackBar("Background image clicked")
            viewModel.getCoffeeMachineConfiguration()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}