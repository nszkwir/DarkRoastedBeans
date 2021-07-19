package com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem

internal data class ExpandableCoffeeItemCardConfiguration(
    var collapsedHeight: Int,
    var collapsable: Boolean,
    var isExpanded: Boolean
)

internal object ExpandableCoffeeItemCardConfigurationFactory {
    fun create(
        expandableCoffeeItemCardAttr: ExpandableCoffeeItemCardAttr
    ): ExpandableCoffeeItemCardConfiguration {
        return ExpandableCoffeeItemCardConfiguration(
            expandableCoffeeItemCardAttr.collapsedHeight,
            expandableCoffeeItemCardAttr.collapsable,
            expandableCoffeeItemCardAttr.isExpanded
        )
    }
}
