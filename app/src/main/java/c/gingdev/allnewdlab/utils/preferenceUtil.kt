package c.gingdev.allnewdlab.utils

import android.content.Context
import android.content.SharedPreferences

class preferenceUtil(context: Context) {
    private val PREF_NAME = "dlab"
    private val PREF_KEY_GRADE = "dlab_Grade"
    private val PREF_KEY_CLASS = "dlab_Class"
    private val PREF: SharedPreferences
            by lazy{ context.getSharedPreferences(PREF_NAME, 0) }

    companion object {
        private var preferenceInstance: preferenceUtil? = null

        fun getInstance(context: Context): preferenceUtil
                = preferenceInstance ?: synchronized(this) {
            preferenceInstance ?: buildPreferenceInstance(context).also { preferenceInstance = it } }

        private fun buildPreferenceInstance(context: Context): preferenceUtil
                = preferenceUtil(context)
    }

    var PREF_GRADE: String?
        get() = PREF.getString(PREF_KEY_GRADE, null)
        set(value) = PREF.edit().putString(PREF_KEY_GRADE, value).apply()

    var PREF_CLASS: String?
        get() = PREF.getString(PREF_KEY_CLASS, null)
        set(value) = PREF.edit().putString(PREF_KEY_CLASS, value).apply()
}