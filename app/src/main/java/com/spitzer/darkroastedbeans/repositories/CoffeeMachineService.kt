package com.spitzer.darkroastedbeans.repositories

import com.spitzer.darkroastedbeans.data.CoffeeMachineConfiguration
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeMachineService {
    @GET("coffee-machine/{id}")
    suspend fun getCoffeeMachineConfiguration(
        @Path("id") machineId: String
    ): Response<CoffeeMachineConfiguration>
}
