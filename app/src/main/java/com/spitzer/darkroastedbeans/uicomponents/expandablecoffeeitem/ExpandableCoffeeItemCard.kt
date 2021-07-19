package com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.spitzer.darkroastedbeans.R
import com.spitzer.darkroastedbeans.extensions.moveLeft
import com.spitzer.darkroastedbeans.model.CoffeeItemExtra
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.adapters.CoffeeExtrasAdapter

class ExpandableCoffeeItemCard : MaterialCardView {

    private lateinit var extrasAdapter: CoffeeExtrasAdapter
    private var listOfExtras: ArrayList<CoffeeItemExtra> = arrayListOf()
    private lateinit var headerLayout: ConstraintLayout
    private lateinit var extrasLayout: ConstraintLayout
    private lateinit var coffeeItemImage: ImageView
    private lateinit var coffeeItemName: TextView
    private lateinit var expandableItemList: RecyclerView

    private lateinit var attr: ExpandableCoffeeItemCardAttr
    private lateinit var configuration: ExpandableCoffeeItemCardConfiguration
    private lateinit var onExtrasClickFunction: (String) -> Unit
    private lateinit var onHeaderClickFunction: () -> Unit

    private var collapsedHeight: Int = DEFAULT_COLLAPSED_HEIGHT
    private var isCollapsable = DEFAULT_COLLAPSABLE
    private var isExpanded = DEFAULT_INITIAL_EXPANDED

    constructor(context: Context) : super(context) {
        val attrs = ExpandableCoffeeItemCardAttr(
            collapsedHeight = DEFAULT_COLLAPSED_HEIGHT,
            collapsable = DEFAULT_COLLAPSABLE,
            isExpanded = DEFAULT_INITIAL_EXPANDED
        )
        initAttrs(attrs)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initAttrs(attributes)
    }

    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(
        context,
        attributes,
        defStyle
    ) {
        initAttrs(attributes)
    }

    private fun initAttrs(attributes: ExpandableCoffeeItemCardAttr) {
        attr = attributes
        val config = ExpandableCoffeeItemCardConfigurationFactory.create(attr)
        setupComponents(config)
    }

    private fun initAttrs(attributes: AttributeSet?) {
        attr = ExpandableCoffeeItemCardAttrParser.parse(context, attributes)
        val config = ExpandableCoffeeItemCardConfigurationFactory.create(attr)
        setupComponents(config)
    }

    private fun setupComponents(config: ExpandableCoffeeItemCardConfiguration) {
        configuration = config

        LayoutInflater.from(context).inflate(R.layout.coffee_item_expandable, this)

        headerLayout = findViewById(R.id.headerLayout)
        extrasLayout = findViewById(R.id.extrasLayout)
        coffeeItemImage = findViewById(R.id.coffeeItemImage)
        coffeeItemName = findViewById(R.id.coffeeItemName)
        expandableItemList = findViewById(R.id.expandableListItems)

        preventCornerOverlap = false
        useCompatPadding = false
        elevation = resources.getDimension(R.dimen.coffee_item_card_elevation)
        radius = resources.getDimension(R.dimen.coffee_item_card_radius)

        collapsedHeight = config.collapsedHeight
        isExpanded = config.isExpanded
        isCollapsable = config.collapsable

        if (isExpanded) {
            setCollapsableEnabled(true)
            expand()
            setCollapsableEnabled(config.collapsable)
        }

        headerLayout.setOnClickListener {
            if (isCollapsable) {
                toggleExpand()
            } else {
                onHeaderClickFunction()
            }
        }

        setViewId()
        requestLayout()
    }

    private fun toggleExpand() {
        if (isExpanded) {
            collapse()
        } else {
            expand()
        }
    }

    private fun expand() {
        extrasLayout.visibility = VISIBLE
        isExpanded = true
    }

    private fun collapse() {
        extrasLayout.visibility = GONE
        isExpanded = false
    }

    fun setItemName(itemName: String) {
        coffeeItemName.text = itemName
    }

    fun setItemIcon(iconDrawable: Drawable) {
        coffeeItemImage.setImageDrawable(iconDrawable)
    }

    fun setExtras(extras: ArrayList<CoffeeItemExtra>) {
        listOfExtras = extras
        extrasAdapter = CoffeeExtrasAdapter(listOfExtras)
        extrasAdapter.onItemClickFunction { position, id ->
            onExtrasClick(position, id)
        }
        expandableItemList.apply {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = extrasAdapter
        }
    }

    private fun onExtrasClick(position: Int, id: String) {
        if (id != "") {
            listOfExtras.forEach {
                if (it.id != id) {
                    it.selected = false
                }
            }
            listOfExtras[position].selected = true
        }
        (expandableItemList.adapter as CoffeeExtrasAdapter).updateData(listOfExtras)
        onExtrasClickFunction(id)
    }

    fun setOnExtrasClickFunction(extrasClickFunction: (String) -> Unit) {
        onExtrasClickFunction = extrasClickFunction
    }

    fun setOnHeaderClickFunction(headerClickFunction: () -> Unit) {
        onHeaderClickFunction = headerClickFunction
    }

    fun setCollapsableEnabled(collapsable: Boolean) {
        isCollapsable = collapsable
    }

    fun hideIcon() {
        coffeeItemImage.apply {
            val pLayoutParams = layoutParams
            pLayoutParams.width = 0
            layoutParams = pLayoutParams
            requestLayout()
        }
        coffeeItemName.moveLeft(-19)
        requestLayout()
    }

    private fun setViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    companion object {
        const val DEFAULT_COLLAPSED_HEIGHT = 10
        const val DEFAULT_COLLAPSABLE = false
        const val DEFAULT_INITIAL_EXPANDED = false
    }
}
