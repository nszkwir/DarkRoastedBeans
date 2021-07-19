package com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.helper

import com.spitzer.darkroastedbeans.R

object CoffeeItemIconHelper {

    var iconsHashMap: HashMap<String, Int?> = HashMap()

    init {
        // ristretto - we don't have the icon
        // typesHashMap["60ba1a062e35f2d9c786c56d"] =
        // capuccino
        iconsHashMap["60be1eabc45ecee5d77ad960"] = R.drawable.ic_cappuchino
        // espresso
        iconsHashMap["60be1db3c45ecee5d77ad890"] = R.drawable.ic_espresso

        // small - venti
        iconsHashMap["60ba3368c45ecee5d77a016b"] = R.drawable.ic_small
        // medium - tall
        iconsHashMap["60ba33dbc45ecee5d77a01f8"] = R.drawable.ic_medium
        // large
        iconsHashMap["60ba18d13ca8c43196b5f606"] = R.drawable.ic_large

        // milk
        iconsHashMap["60ba34a0c45ecee5d77a0263"] = R.drawable.ic_milk
        // sugar
        iconsHashMap["60ba197c2e35f2d9c786c525"] = R.drawable.ic_espresso
    }

    fun getIcon(id: String): Int? {
        return try {
            iconsHashMap.getValue(id)
        } catch (e: Exception) {
            null
        }
    }
}