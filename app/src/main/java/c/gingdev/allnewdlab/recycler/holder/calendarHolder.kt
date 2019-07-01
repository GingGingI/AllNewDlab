package c.gingdev.allnewdlab.recycler.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.utils.monthlyColor
import kotlinx.android.synthetic.main.layout_calendar_items.view.*

class calendarHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_calendar_items, parent, false)) {

    fun bind(item: String) {
        val pair = divideItem(item)
        itemView.run {
            date.text = pair.first
            content.text = pair.second

            date.setTextColor(getTextColor())
        }
    }

    private val reg = Regex("""[^0-9]""")
    private fun divideItem(item: String): Pair<String, String> {
        val date = reg.replace(item.split(":")[0], "")
        val content = item.split(":")[1]

        return Pair(date, content)
    }

    private val mColor = monthlyColor.getInstance()
    private fun getTextColor(): Int
            = mColor.getMonthlyColor(parent.context)
}