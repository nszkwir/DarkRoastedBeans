package com.spitzer.darkroastedbeans.ui.coffeeselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.databinding.CoffeeSelectionFragmentBinding

class CoffeeSelectionFragment : BaseFragment() {

    private var _binding: CoffeeSelectionFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoffeeSelectionFragmentViewModel
    override fun getViewModel() = viewModel

    private val args: CoffeeSelectionFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CoffeeSelectionFragmentViewModelFactory()
        ).get(CoffeeSelectionFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoffeeSelectionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCoffeeSelectionModel(args.model)
        setupView()
    }

    private fun setupView() {
        binding.styleId.text = viewModel.getStyleId()
        binding.sizeId.text = viewModel.getSizeId()
        binding.extrasId.text = viewModel.getExtrasDetail()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
