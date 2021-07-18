package com.spitzer.darkroastedbeans.model

import android.os.Parcelable
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeExtra
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeMachineConfiguration
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeSize
import com.spitzer.darkroastedbeans.repositories.coffeemachine.data.CoffeeType
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class CoffeeSelectionModel(
    val machineConfiguration: CoffeeMachineConfiguration,
    var styleId: String? = "",
    var sizeId: String? = "",
    var extraId: String? = "",
    var extraSubselectionId: String? = ""
) : Parcelable {

    @IgnoredOnParcel
    val availableTypes  = machineConfiguration.types?.map {
        Pair(it.id, it.name) } as ArrayList<Pair<String, String>>

    fun getTypes() = machineConfiguration.types?.map {
        CoffeeItem(
            it.id,
            it.name,
            false
        )
    } as ArrayList<CoffeeItem>

    fun getSizesByStyle(): ArrayList<CoffeeItem> {
        val typeSelected = machineConfiguration.types?.filter { coffeeType ->
            coffeeType.id == styleId.toString()
        } as ArrayList<CoffeeType>

        val sizes =  machineConfiguration.sizes?.filter {
            typeSelected.first().sizes.contains(it.id)
        } as ArrayList<CoffeeSize>

        return sizes.map { CoffeeItem(
            it.id,
            it.name,
            false
        ) } as ArrayList<CoffeeItem>
    }

    fun getExtrasByStyle(): ArrayList<CoffeeItem> {
        val typeSelected = machineConfiguration.types?.filter { coffeeType ->
            coffeeType.id == styleId.toString()
        } as ArrayList<CoffeeType>

        val extras = machineConfiguration.extras?.filter {
            typeSelected.first().extras.contains(it.id)
        } as ArrayList<CoffeeExtra>

        return extras.map { CoffeeItem(
            it.id,
            it.name,
            !it.subselections.isNullOrEmpty(),
            it.subselections?.map { itemExtra ->
                CoffeeItemExtra(
                    itemExtra.id,
                    itemExtra.name,
                    false
                )
            } as ArrayList<CoffeeItemExtra>
        ) } as ArrayList<CoffeeItem>
    }
}
