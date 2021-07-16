package com.spitzer.darkroastedbeans.repositories.coffeemachine.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeSize(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String = ""
) : Parcelable
