package com.spitzer.darkroastedbeans.repositories.coffeemachine

import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeMachineConfiguration
import com.spitzer.network.ResultData

interface CoffeeMachineRepository {
    suspend fun getCoffeeMachineConfiguration(
        machineId: String
    ): ResultData<CoffeeMachineConfiguration?>
}
