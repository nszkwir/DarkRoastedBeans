package com.spitzer.darkroastedbeans.ui.coffeestyle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.databinding.CoffeeStyleFragmentBinding
import com.spitzer.darkroastedbeans.ui.machinepairing.MachinePairingFragmentViewModel

class CoffeeStyleFragment : BaseFragment() {
    private var _binding: CoffeeStyleFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MachinePairingFragmentViewModel

    override fun getViewModel() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CoffeeStyleFragmentViewModelFactory()
        ).get(MachinePairingFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoffeeStyleFragmentBinding.inflate(inflater, container, false)
        setupView()
        defineObservables()
        return binding.root
    }

    fun defineObservables() {
        //findNavController().navigate()
    }

    fun setupView() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
