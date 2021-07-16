package com.spitzer.darkroastedbeans.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeType(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("sizes")
    var sizes: ArrayList<String> = arrayListOf(),
    @SerializedName("extras")
    var extras: ArrayList<String> = arrayListOf(),
) : Parcelable
