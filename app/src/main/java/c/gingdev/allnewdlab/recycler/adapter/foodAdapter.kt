package c.gingdev.allnewdlab.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.recycler.holder.Rice
import c.gingdev.allnewdlab.recycler.holder.SideDish
import c.gingdev.allnewdlab.recycler.holder.Soup
import c.gingdev.allnewdlab.recycler.holder.foodHolder

class foodAdapter(private val items: List<String>): RecyclerView.Adapter<foodHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodHolder
        = foodHolder(parent = parent)

    override fun getItemCount(): Int
            = items.size

    override fun onBindViewHolder(holder: foodHolder, position: Int) {
        holder.bind(getItemType(position), items[position])
    }

    private fun getItemType(position: Int): Int
        = when (position) {
            0 -> { Rice }
            1 -> { Soup }
            else -> { SideDish }
    }

}