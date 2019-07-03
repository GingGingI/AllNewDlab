package c.gingdev.allnewdlab.recycler.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.R
import c.gingdev.allnewdlab.utils.monthlyColor
import kotlinx.android.synthetic.main.layout_calendar_items.view.*
import kotlinx.android.synthetic.main.layout_schedule_items.view.*


class scheduleHolder(val parent: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.layout_schedule_items, parent, false)) {

    private val reg = Regex("""\s+""")
    private val numReg = Regex("""[^0-9]""")
    fun bind(item: String) {
        itemView.run {
            val items = reg.split(item)
            if (items.size > 1) {
                val period_time = numReg.replace(items[0], "")
                val classroom = items[1]
                val classname = items[2]

                period.setTextColor(getTextColor())
                period.text = period_time
                className.text = classname
                if (classroom == "0호")
                    classRoom.text = "창체활동"
                else
                    classRoom.text = classroom
            }
        }
    }

    private val mColor = monthlyColor.getInstance()
    private fun getTextColor(): Int
            = mColor.getMonthlyColor(parent.context)
}