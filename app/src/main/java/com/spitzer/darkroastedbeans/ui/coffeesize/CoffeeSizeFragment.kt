package com.spitzer.darkroastedbeans.ui.coffeesize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.databinding.CoffeeSizeFragmentBinding
import com.spitzer.darkroastedbeans.model.CoffeeSelectionModel
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.adapters.CoffeeItemAdapter

class CoffeeSizeFragment : BaseFragment() {

    private var _binding: CoffeeSizeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoffeeSizeFragmentViewModel
    override fun getViewModel() = viewModel

    private lateinit var coffeeItemAdapter: CoffeeItemAdapter
    private var model: CoffeeSelectionModel? = null
    val args: CoffeeSizeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CoffeeSizeFragmentViewModelFactory()
        ).get(CoffeeSizeFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoffeeSizeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = args.model

        if (model == null || model?.machineConfiguration == null) {
            findNavController().popBackStack(R.id.MachinePairingFragment, false)
        }

        coffeeItemAdapter = CoffeeItemAdapter(
            (model as CoffeeSelectionModel).getSizesByStyle(),
            { headerId -> onHeaderClick(headerId) },
            { headerId, extraId -> onExtrasClick(headerId, extraId) })

        binding.coffeeSizesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = coffeeItemAdapter
            adapter?.notifyDataSetChanged()
        }
    }

    private fun onHeaderClick(headerId: String) {
        model?.let {
            if (headerId.isNotEmpty()) {
                model?.sizeId = headerId
                val action = CoffeeSizeFragmentDirections
                    .actionCoffeeSizeFragmentToCoffeeExtrasFragment(model!!)
                findNavController().navigate(action)
            }
        }
        //showSnackBar("HeaderId: $headerId")
    }

    private fun onExtrasClick(headerId: String, extraId: String) {
        // showSnackBar("HeaderId: $headerId - ExtraId: $extraId")
        // This function should not be executed
        // The styles shown has no extras
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
