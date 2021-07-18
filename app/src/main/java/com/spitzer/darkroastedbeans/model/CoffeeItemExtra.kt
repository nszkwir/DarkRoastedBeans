package com.spitzer.darkroastedbeans.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeItemExtra(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("selected")
    var selected: Boolean = false
) : Parcelable