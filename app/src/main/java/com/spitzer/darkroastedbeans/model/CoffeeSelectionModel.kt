package com.spitzer.darkroastedbeans.model

import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeMachineConfiguration

class CoffeeSelectionModel(
    val machineConfiguration: CoffeeMachineConfiguration,
    var styleId: String? = "",
    var sizeId: String? = "",
    var extraId: String? = "",
    var extraSubselectionId: String? = ""
)
