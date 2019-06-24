package c.gingdev.allnewdlab.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.graphics.ColorUtils

class ColorChecker {
    val LightColor = true
    val DarkColor = false

    private var color: Int = 0

    /**
     * @param view only solid color Not Gradient or Image
     */

    constructor(view: View) {
        val bg = view.background
        if (bg is ColorDrawable)
            this.color = bg.color
        else
            this.color = Color.WHITE
    }

    /**
     * @param color color of view
     * */
    constructor(color: Int) {
        this.color = color
    }

    /**
     * @return boolean
     */
    fun isLight(): Boolean
            = ColorUtils.calculateLuminance(color) > 0.5
}