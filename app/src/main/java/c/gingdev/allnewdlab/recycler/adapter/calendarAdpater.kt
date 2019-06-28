package c.gingdev.allnewdlab.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.recycler.holder.calendarHolder

class calendarAdapter(private val items: List<String>): RecyclerView.Adapter<calendarHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): calendarHolder
            = calendarHolder(parent = parent)

    override fun getItemCount(): Int
            = items.size

    override fun onBindViewHolder(holder: calendarHolder, position: Int) {
        holder.bind(items[position])
    }
}