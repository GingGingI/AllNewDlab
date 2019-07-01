package c.gingdev.allnewdlab.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.recycler.holder.copyRightHolder


class copyRightAdapter(private val items: List<Pair<String, String>>): RecyclerView.Adapter<copyRightHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): copyRightHolder
            = copyRightHolder(parent = parent)

    override fun getItemCount(): Int
            = items.size

    override fun onBindViewHolder(holder: copyRightHolder, position: Int) {
        holder.bind(items[position].first, items[position].second)
    }
}