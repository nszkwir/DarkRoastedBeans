package com.spitzer.darkroastedbeans.mainfragment

import androidx.lifecycle.ViewModel
import com.spitzer.darkroastedbeans.repositories.CoffeeMachineRepository
import com.spitzer.darkroastedbeans.repositories.CoffeeMachineRepositoryImpl
import com.spitzer.network.ResultData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MainFragmentViewModel(
    override val coroutineContext: CoroutineContext,
    private val repository: CoffeeMachineRepository = CoffeeMachineRepositoryImpl()
) : ViewModel(), CoroutineScope {

    private val machineId = "60ba1ab72e35f2d9c786c610"

    fun getCoffeeMachineConfiguration() {
        this.requestCoffeeMachineConfiguration()
    }

    private fun requestCoffeeMachineConfiguration() = launch {
        when (val result = repository.getCoffeeMachineConfiguration(machineId)) {
            is ResultData.Success -> {
                if (result.data != null) {

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
