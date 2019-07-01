package c.gingdev.allnewdlab.recycler.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.*
import kotlinx.android.synthetic.main.layout_copyright_items.view.*

class copyRightHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_copyright_items, parent, false)) {

    fun bind(itemUrl: String, item: String) {
        itemView.run {
            content.text = item
            url.text = itemUrl
        }

        itemView.setOnClickListener {
            Log.e("url", itemUrl)
        }
    }
}