package c.gingdev.allnewdlab.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import c.gingdev.allnewdlab.fragments.calendarFragment
import c.gingdev.allnewdlab.fragments.foodFragment
import c.gingdev.allnewdlab.fragments.scheduleFragment

class fragmentPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val items = arrayListOf<Fragment>()

    override fun getItem(position: Int): Fragment = when (items[position]) {
        is foodFragment -> { foodFragment.getInstance() }
        is calendarFragment -> { calendarFragment.getInstance() }
        is scheduleFragment -> { scheduleFragment.getInstance() }
        else -> Fragment()
    }

    override fun getCount(): Int
            = items.size

    fun addItem(fm: Fragment) { items.add(fm) }
}