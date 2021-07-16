package com.spitzer.darkroastedbeans.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeMachineConfiguration(
    @SerializedName("_id")
    var id: String,
    @SerializedName("types")
    var types: ArrayList<CoffeeType>? = arrayListOf(),
    @SerializedName("sizes")
    var sizes: ArrayList<CoffeeSize>? = arrayListOf(),
    @SerializedName("extras")
    var extras: ArrayList<CoffeeExtra>? = arrayListOf(),
) : Parcelable
