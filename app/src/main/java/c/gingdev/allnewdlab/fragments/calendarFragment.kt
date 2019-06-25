package c.gingdev.allnewdlab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import c.gingdev.allnewdlab.R


class calendarFragment: Fragment() {
    companion object {
        private var Instance: Fragment? = null

        fun getInstance(): Fragment
                = Instance ?: synchronized(this) {
            Instance ?: buildFragment().also { Instance = it }}

        private fun buildFragment(): Fragment
                = calendarFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_layout_calendar, container, false)

}