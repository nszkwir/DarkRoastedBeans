package com.spitzer.darkroastedbeans.mainfragment

import com.spitzer.darkroastedbeans.core.BaseViewModel
import com.spitzer.darkroastedbeans.data.CoffeeMachineConfiguration
import com.spitzer.darkroastedbeans.repositories.CoffeeMachineRepository
import com.spitzer.darkroastedbeans.repositories.CoffeeMachineRepositoryImpl
import com.spitzer.network.ResultData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MainFragmentViewModel(
    override val coroutineContext: CoroutineContext,
    private val repository: CoffeeMachineRepository = CoffeeMachineRepositoryImpl()
) : BaseViewModel(), CoroutineScope {

    private val machineId = "60ba1ab72e35f2d9c786c610"
    private var config: CoffeeMachineConfiguration? = null

    init {
        setToolbarConfiguration(
            "Dark Roasted Beans",
            "Tab the machine to start",
            false
        )
    }

    fun getCoffeeMachineConfiguration() {
        this.requestCoffeeMachineConfiguration()
    }

    private fun requestCoffeeMachineConfiguration() = launch {
        when (val result = repository.getCoffeeMachineConfiguration(machineId)) {
            is ResultData.Success -> {
                if (result.data != null) {
                    config = result.data
                } else {

                }
            }
            is ResultData.Error -> {
                if (result.isNetworkError()) {
                } else {
                }
            }
        }
    }
}
