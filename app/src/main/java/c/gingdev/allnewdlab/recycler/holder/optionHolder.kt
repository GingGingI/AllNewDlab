package c.gingdev.allnewdlab.recycler.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.createBy
import c.gingdev.allnewdlab.grade
import c.gingdev.allnewdlab.openSource
import kotlinx.android.synthetic.main.layout_option_items.view.*


class optionHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_option_items, parent, false)) {

    fun bind(itemId: Int, item: String) {
        itemView.run {
            content.text = item
        }

        itemView.setOnClickListener {
            Toast.makeText(parent.context, "itemID : $itemId", Toast.LENGTH_SHORT).show()
            when(itemId) {
                grade -> {

                }
                createBy -> {

                }
                openSource -> {

                }
            }
        }
    }
}