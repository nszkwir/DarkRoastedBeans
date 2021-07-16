package com.spitzer.darkroastedbeans.repositories

import com.spitzer.darkroastedbeans.data.CoffeeMachineConfiguration
import com.spitzer.network.ResultData

interface CoffeeMachineRepository {
    suspend fun getCoffeeMachineConfiguration(
        machineId: String
    ): ResultData<CoffeeMachineConfiguration?>
}
