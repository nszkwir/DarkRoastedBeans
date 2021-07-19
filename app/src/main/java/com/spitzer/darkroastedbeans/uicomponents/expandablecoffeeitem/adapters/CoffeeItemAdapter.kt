package com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.darkroastedbeans.databinding.CoffeeItemBinding
import com.spitzer.darkroastedbeans.model.CoffeeItem
import com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.helper.CoffeeItemIconHelper

class CoffeeItemAdapter(
    private var items: ArrayList<CoffeeItem>,
    private var onItemClick: (String) -> Unit,
    private var onExtrasClick: (String, String) -> Unit
) : RecyclerView.Adapter<CoffeeItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val item =
            CoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item, onItemClick, onExtrasClick)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setOnItemClickFunction(itemClickFunction: (String) -> Unit) {
        onItemClick = itemClickFunction
    }

    fun setOnExtrasClickFunction(extrasClickFunction: (String, String) -> Unit) {
        onExtrasClick = extrasClickFunction
    }

    inner class ViewHolder(
        private val itemBinding: CoffeeItemBinding,
        private val itemClickFunction: (String) -> Unit,
        private val extrasClickFunction: (String, String) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private lateinit var headerId: String
        fun bind(item: CoffeeItem) {
            headerId = item.id
            itemBinding.expandableItem.setItemName(item.name)
            val iconDrawableId = CoffeeItemIconHelper.getIcon(item.id)
            if (iconDrawableId == null) {
                itemBinding.expandableItem.hideIcon()
            } else {
                itemBinding.expandableItem.setItemIcon(
                    ContextCompat.getDrawable(
                        itemBinding.root.context,
                        iconDrawableId
                    )!!
                )
            }

            if (item.expandable && item.extras.isNotEmpty()) {
                itemBinding.expandableItem.setCollapsableEnabled(item.expandable)
                itemBinding.expandableItem.setExtras(item.extras)
                itemBinding.expandableItem.setOnExtrasClickFunction { extraId ->
                    onExtrasClick(extraId)
                }
            } else {
                itemBinding.expandableItem.setOnHeaderClickFunction { onHeaderClick() }
            }
        }

        private fun onHeaderClick() {
            itemClickFunction(headerId)
        }

        private fun onExtrasClick(extraId: String) {
            extrasClickFunction(headerId, extraId)
        }
    }
}
