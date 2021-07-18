package com.spitzer.darkroastedbeans.ui.coffeeextra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.databinding.CoffeeExtrasFragmentBinding
import com.spitzer.darkroastedbeans.databinding.CoffeeSizeFragmentBinding
import com.spitzer.darkroastedbeans.databinding.CoffeeStyleFragmentBinding
import com.spitzer.darkroastedbeans.model.CoffeeSelectionModel
import com.spitzer.darkroastedbeans.ui.coffeestyle.CoffeeStyleFragmentArgs
import com.spitzer.darkroastedbeans.ui.coffeestyle.CoffeeStyleFragmentDirections
import com.spitzer.darkroastedbeans.ui.machinepairing.MachinePairingFragmentViewModel
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.adapters.CoffeeItemAdapter

class CoffeeExtrasFragment : BaseFragment() {

    private var _binding: CoffeeExtrasFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoffeeExtrasFragmentViewModel
    override fun getViewModel() = viewModel

    private lateinit var coffeeItemAdapter: CoffeeItemAdapter
    private var model: CoffeeSelectionModel? = null
    val args: CoffeeExtrasFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CoffeeExtrasFragmentViewModelFactory()
        ).get(CoffeeExtrasFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoffeeExtrasFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = args.model

        if (model == null || model?.machineConfiguration == null) {
            findNavController().popBackStack(R.id.MachinePairingFragment, false)
        }

        coffeeItemAdapter = CoffeeItemAdapter(
            (model as CoffeeSelectionModel).getExtrasByStyle(),
            { headerId -> onHeaderClick(headerId) },
            { headerId, extraId -> onExtrasClick(headerId, extraId) })

        binding.coffeeExtrasRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = coffeeItemAdapter
            adapter?.notifyDataSetChanged()
        }
    }

    private fun onHeaderClick(headerId: String) {
//        model?.let {
//            if (headerId.isNotEmpty()) {
//                model?.sizeId = headerId
//                val action = CoffeeSizeFragmentDirections
//                    .actionCoffeeSizeFragmentToCoffeeExtrasFragment(model!!)
//                findNavController().navigate(action)
//            }
//        }
        showSnackBar("HeaderId: $headerId")
    }

    private fun onExtrasClick(headerId: String, extraId: String) {
         showSnackBar("HeaderId: $headerId - ExtraId: $extraId")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
