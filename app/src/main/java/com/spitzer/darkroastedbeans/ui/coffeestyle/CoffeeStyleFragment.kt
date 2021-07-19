package com.spitzer.darkroastedbeans.ui.coffeestyle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.darkroastedbeans.core.BaseFragment
import com.spitzer.darkroastedbeans.databinding.CoffeeStyleFragmentBinding
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.adapters.CoffeeItemAdapter

class CoffeeStyleFragment : BaseFragment() {

    private var _binding: CoffeeStyleFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoffeeStyleFragmentViewModel
    override fun getViewModel() = viewModel

    private lateinit var coffeeItemAdapter: CoffeeItemAdapter

    private val args: CoffeeStyleFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CoffeeStyleFragmentViewModelFactory()
        ).get(CoffeeStyleFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoffeeStyleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCoffeeSelectionModel(args.model)

        coffeeItemAdapter = CoffeeItemAdapter(
            viewModel.coffeeSelectionModel.value!!.getTypes(),
            { headerId -> onHeaderClick(headerId) },
            { headerId, extraId -> onExtrasClick(headerId, extraId) })

        binding.coffeeStyleRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = coffeeItemAdapter
            adapter?.notifyDataSetChanged()
        }
    }

    private fun onHeaderClick(headerId: String) {
        if (headerId.isNotEmpty()) {
            viewModel.coffeeSelectionModel.value!!.styleId = headerId
            val action = CoffeeStyleFragmentDirections
                .actionCoffeeStyleFragmentToCoffeeSizeFragment(
                    viewModel.coffeeSelectionModel.value!!
                )
            findNavController().navigate(action)
        }
    }

    private fun onExtrasClick(headerId: String, extraId: String) {
        // TODO handle coffee styles with extras if possible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
