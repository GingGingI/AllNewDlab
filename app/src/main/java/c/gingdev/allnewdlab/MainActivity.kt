package c.gingdev.allnewdlab

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import c.gingdev.allnewdlab.fragments.calendarFragment
import c.gingdev.allnewdlab.fragments.foodFragment
import c.gingdev.allnewdlab.utils.ColorChecker
import c.gingdev.allnewdlab.utils.fragmentPagerAdapter
import c.gingdev.allnewdlab.utils.monthlyColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStatusBar()
        setFragment()
        setNavigation()

        Runnable {
            backGroundColorChanged()
        }.run()
    }

    private fun initStatusBar() {
        clearStatusBar()
        statusBar.layoutParams = ConstraintLayout.LayoutParams(statusBar.width, getStatusBarHeight())
    }
    private fun clearStatusBar() {
        window.apply {
                setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceID = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceID > 0)
            result = resources.getDimensionPixelSize(resourceID)
        return result
    }

    private fun backGroundColorChanged() {
        var color: Int
        if (ColorChecker(parentView).isLight()) {
//            현재 어두움.
            color = ContextCompat.getColor(this, R.color.colorBlack)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
//            현제 밝음
            color = ContextCompat.getColor(this, R.color.colorWhite)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                window.decorView.systemUiVisibility = 0
        }

        options.setColorFilter(color)
    }

    private lateinit var adapter: fragmentPagerAdapter
    private val fmList = arrayListOf<Fragment>()
    private fun setFragment() {
        adapter = fragmentPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addItem(foodFragment.getInstance())
        adapter.addItem(calendarFragment.getInstance())

        pager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            this@apply.adapter = this@MainActivity.adapter
            setPageTransformer(MarginPageTransformer(1500))
            offscreenPageLimit = this@MainActivity.adapter.itemCount
            isUserInputEnabled = false }
    }

    private val mColor = monthlyColor.getInstance()
    private fun setNavigation() {
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.food -> {
                    pager.setCurrentItem(0, true)
                    pagerTitle.text = resources.getText(R.string.todaysLunch)
                }
                R.id.calendar -> {
                    pager.setCurrentItem(1, true)
                    pagerTitle.text = resources.getText(R.string.monthlySchedule)
                }
                else -> {
                    pager.setCurrentItem(0, true)
                    pagerTitle.text = resources.getText(R.string.errorString)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            buildColorStateList(mColor.getMonthlyColor(this))
                .also {
                    bottomNav.itemTextColor = it
                    bottomNav.itemIconTintList = it }
        }
    }
    private fun buildColorStateList(highlitedColor: Int): ColorStateList {
        val states = arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_checked)
        )
        val colors = intArrayOf(
            Color.parseColor("#747474"),
            highlitedColor)

        return ColorStateList(states, colors)
    }
}
