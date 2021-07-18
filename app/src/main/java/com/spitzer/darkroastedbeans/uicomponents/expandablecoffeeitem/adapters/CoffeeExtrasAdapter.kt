package com.spitzer.darkroastedbeans.uicomponents.expandablecoffeeitem.adapters

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spitzer.darkroastedbeans.databinding.CoffeeExtrasBinding
import com.spitzer.darkroastedbeans.extensions.listenToClick
import com.spitzer.darkroastedbeans.model.CoffeeItemExtra

class CoffeeExtrasAdapter(
    private var items : ArrayList<CoffeeItemExtra>
) : RecyclerView.Adapter<CoffeeExtrasAdapter.ViewHolder>() {

    lateinit var onItemClick: (Int,String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val item =
            CoffeeExtrasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item).listenToClick { position, _ ->
            if (items[position].selected) {
                items[position].selected = false
                onItemClick(position, "")
            } else {
                onItemClick(position, items[position].id)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun onItemClickFunction(itemClickFunction: (Int, String) -> Unit) {
        onItemClick = itemClickFunction
    }

    fun updateData(updatedData: ArrayList<CoffeeItemExtra>) {
        items = updatedData
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val itemBinding: CoffeeExtrasBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: CoffeeItemExtra) {
            itemBinding.extraName.text = item.name
            itemBinding.checkImage.visibility = if (item.selected) {
                VISIBLE
            } else {
                GONE
            }
        }
    }
}
