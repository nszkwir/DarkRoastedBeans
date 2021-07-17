package com.spitzer.darkroastedbeans.model

import android.os.Parcelable
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeMachineConfiguration
import kotlinx.parcelize.Parcelize

@Parcelize
class CoffeeSelectionModel(
    val machineConfiguration: CoffeeMachineConfiguration,
    var styleId: String? = "",
    var sizeId: String? = "",
    var extraId: String? = "",
    var extraSubselectionId: String? = ""
) : Parcelable
