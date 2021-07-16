package com.spitzer.darkroastedbeans.repositories

import com.spitzer.darkroastedbeans.data.CoffeeMachineConfiguration
import com.spitzer.darkroastedbeans.utils.safeCall
import com.spitzer.network.ApiClient
import com.spitzer.network.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoffeeMachineRepositoryImpl(
    private val service: CoffeeMachineService = ApiClient()
        .createService(CoffeeMachineService::class.java),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CoffeeMachineRepository {
    override suspend fun getCoffeeMachineConfiguration(
        machineId: String
    ): ResultData<CoffeeMachineConfiguration?> {
        return withContext(dispatcher) {
            return@withContext safeCall { service.getCoffeeMachineConfiguration(machineId) }
        }
    }
}
