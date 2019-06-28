package c.gingdev.allnewdlab.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import c.gingdev.allnewdlab.R
import java.util.*

class monthlyColor {
    companion object {
        private var monthlyColorInstance: monthlyColor? = null

        fun getInstance(): monthlyColor
                = monthlyColorInstance ?: synchronized(this) {
            monthlyColorInstance ?: buildMonthlyColorInstance().also { monthlyColorInstance = it } }

        private fun buildMonthlyColorInstance(): monthlyColor
                = monthlyColor()
    }

    fun getMonthlyColor(context: Context): Int {
        val month = (Calendar.getInstance().get(Calendar.MONTH) + 1)
        return getColor(month, context)
    }

    private fun getColor(month: Int, context: Context)
            = when(month) {
        1 -> { ContextCompat.getColor(context, R.color.month1)}
        2 -> { ContextCompat.getColor(context, R.color.month2)}
        3 -> { ContextCompat.getColor(context, R.color.month3)}
        4 -> { ContextCompat.getColor(context, R.color.month4)}
        5 -> { ContextCompat.getColor(context, R.color.month5)}
        6 -> { ContextCompat.getColor(context, R.color.month6)}
        7 -> { ContextCompat.getColor(context, R.color.month7)}
        8 -> { ContextCompat.getColor(context, R.color.month8)}
        9 -> { ContextCompat.getColor(context, R.color.month9)}
        10 -> { ContextCompat.getColor(context, R.color.month10)}
        11 -> { ContextCompat.getColor(context, R.color.month11)}
        12 -> { ContextCompat.getColor(context, R.color.month12)}
        else -> { ContextCompat.getColor(context, R.color.month1)} }
}