package c.gingdev.allnewdlab.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.recycler.holder.optionHolder


class optionAdapter(private val items: List<Pair<Int, String>>): RecyclerView.Adapter<optionHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionHolder
            = optionHolder(parent = parent)

    override fun getItemCount(): Int
            = items.size

    override fun onBindViewHolder(holder: optionHolder, position: Int) {
        holder.bind(items[position].first, items[position].second)
    }
}