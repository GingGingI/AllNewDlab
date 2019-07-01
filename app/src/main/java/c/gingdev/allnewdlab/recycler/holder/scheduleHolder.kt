package c.gingdev.allnewdlab.recycler.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.utils.monthlyColor


class scheduleHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_schedule_items, parent, false)) {

    fun bind(item: String) {
        itemView.run {
            Log.e("item", item)
        }
    }

    private val mColor = monthlyColor.getInstance()
    private fun getTextColor(): Int
            = mColor.getMonthlyColor(parent.context)
}