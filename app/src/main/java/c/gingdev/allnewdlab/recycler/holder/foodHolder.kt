package c.gingdev.allnewdlab.recycler.holder

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.R
import kotlinx.android.synthetic.main.layout_food_items.view.*

const val Rice = 0
const val Soup = 1
const val SideDish = 2

class foodHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_food_items, parent, false)) {

    fun bind(type: Int, item: String) {
        itemView.run {
            content.text = item
            icon.setImageDrawable(getIcon(type))
        }
    }

    private fun getIcon(type: Int): Drawable?
        = when(type) {
        Rice -> { parent.context.resources.getDrawable(R.drawable.ic_rice) }
        Soup -> { parent.context.resources.getDrawable(R.drawable.ic_soup) }
        else -> parent.context.resources.getDrawable(R.drawable.ic_side_dish)
    }

}