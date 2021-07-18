package com.spitzer.darkroastedbeans.ui.machinepairing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.databinding.MachinePairingFragmentBinding
import com.spitzer.darkroastedbeans.model.CoffeeSelectionModel

class MachinePairingFragment : BaseFragment() {
    private var _binding: MachinePairingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MachinePairingFragmentViewModel

    override fun getViewModel() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MachinePairingFragmentViewModelFactory()
        ).get(MachinePairingFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MachinePairingFragmentBinding.inflate(inflater, container, false)
        setupView()
        defineObservables()
        return binding.root
    }

    fun defineObservables() {
        viewModel.coffeeMachineConfiguration.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { coffeeMachineConfiguration ->
                hideProgress()
                val action = MachinePairingFragmentDirections
                    .actionMachinePairingFragmentToCoffeeStyleFragment(
                        CoffeeSelectionModel(coffeeMachineConfiguration)
                    )
                findNavController().navigate(action)
            }
        })
    }

    fun setupView() {
        binding.backgroundImage.setOnClickListener {
            showProgress()
            viewModel.getCoffeeMachineConfiguration()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
