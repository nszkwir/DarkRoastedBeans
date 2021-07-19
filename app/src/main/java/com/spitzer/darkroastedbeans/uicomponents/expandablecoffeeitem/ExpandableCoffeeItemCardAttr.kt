package com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem

import android.content.Context
import android.util.AttributeSet
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.ExpandableCoffeeItemCard.Companion.DEFAULT_COLLAPSABLE
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.ExpandableCoffeeItemCard.Companion.DEFAULT_COLLAPSED_HEIGHT
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.ExpandableCoffeeItemCard.Companion.DEFAULT_INITIAL_EXPANDED

internal data class ExpandableCoffeeItemCardAttr(
    val collapsedHeight: Int,
    val collapsable: Boolean,
    val isExpanded: Boolean
)

internal object ExpandableCoffeeItemCardAttrParser {
    fun parse(context: Context, attr: AttributeSet?): ExpandableCoffeeItemCardAttr {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.ExpandableCoffeeItemCard)

        val collapsedHeight = typedArray.getInteger(
            R.styleable.ExpandableCoffeeItemCard_collapsedHeight,
            DEFAULT_COLLAPSED_HEIGHT
        )
        val collapsable = typedArray.getBoolean(
            R.styleable.ExpandableCoffeeItemCard_collapsable,
            DEFAULT_COLLAPSABLE
        )
        val isExpanded = typedArray.getBoolean(
            R.styleable.ExpandableCoffeeItemCard_isExpanded,
            DEFAULT_INITIAL_EXPANDED
        )

        typedArray.recycle()

        return ExpandableCoffeeItemCardAttr(
            collapsedHeight,
            collapsable,
            isExpanded
        )
    }
}
