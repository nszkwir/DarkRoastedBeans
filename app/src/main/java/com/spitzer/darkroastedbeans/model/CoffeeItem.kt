package com.spitzer.darkroastedbeans.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class CoffeeItem(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("expandable")
    var expandable: Boolean = false,
    @SerializedName("extras")
    var extras: ArrayList<CoffeeItemExtra> = arrayListOf()
) : Parcelable