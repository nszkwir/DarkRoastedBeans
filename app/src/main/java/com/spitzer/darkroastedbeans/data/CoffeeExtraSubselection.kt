package com.spitzer.darkroastedbeans.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeExtraSubselection(
    @SerializedName("_id")
    var id: String,
    @SerializedName("name")
    var name: String = ""
) : Parcelable

