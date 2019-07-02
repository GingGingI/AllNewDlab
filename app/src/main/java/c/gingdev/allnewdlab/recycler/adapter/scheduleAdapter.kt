package c.gingdev.allnewdlab.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.recycler.holder.scheduleHolder

class scheduleAdapter(private val items: List<String>): RecyclerView.Adapter<scheduleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): scheduleHolder
            = scheduleHolder(parent = parent)

    override fun getItemCount(): Int
            = items.size - 1

    override fun onBindViewHolder(holder: scheduleHolder, position: Int) {
        holder.bind(items[position])
    }

}