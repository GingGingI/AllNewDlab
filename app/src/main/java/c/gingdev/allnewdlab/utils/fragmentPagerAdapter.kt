package c.gingdev.allnewdlab.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import c.gingdev.allnewdlab.fragments.calendarFragment
import c.gingdev.allnewdlab.fragments.foodFragment

class fragmentPagerAdapter(fm: FragmentManager, lifeCycle: Lifecycle): FragmentStateAdapter(fm, lifeCycle) {
    private val items = arrayListOf<Fragment>()

    override fun getItemCount(): Int
        = items.size

    override fun createFragment(position: Int): Fragment
        = when (items[position]) {
            is foodFragment -> { foodFragment.getInstance() }
            is calendarFragment -> { calendarFragment.getInstance() }
            else -> calendarFragment.getInstance()
        }

    fun addItem(fm: Fragment) { items.add(fm) }
}