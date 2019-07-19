package c.gingdev.allnewdlab

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import c.gingdev.allnewdlab.fragments.calendarFragment
import c.gingdev.allnewdlab.fragments.foodFragment
import c.gingdev.allnewdlab.fragments.scheduleFragment
import c.gingdev.allnewdlab.utils.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStatusBar()
        setFragment()
        setNavigation()
        initLayout()

        Runnable {
            backGroundColorChanged()
        }.run()
    }

    private fun initStatusBar() {
        clearStatusBar()
        statusBar.layoutParams = ConstraintLayout.LayoutParams(statusBar.width, getStatusBarHeight())
    }
    private fun clearStatusBar() {
        window.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = 0x00000000  // transparent
            } else {
                addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }

            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
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

    private fun initLayout() {
        options.setOnClickListener {
            startRevealActivity(it) }
    }

    private lateinit var fragmentAdpater: fragmentPagerAdapter
    private val fmList = arrayListOf<Fragment>().apply {
        add(foodFragment.getInstance())
        add(scheduleFragment.getInstance())
        add(calendarFragment.getInstance())
    }
    private fun setFragment() {
        fragmentAdpater = fragmentPagerAdapter(supportFragmentManager)
        fragmentAdpater.addItem(foodFragment())
        fragmentAdpater.addItem(scheduleFragment())
        fragmentAdpater.addItem(calendarFragment())

        pager.apply {
            adapter = fragmentAdpater
            offscreenPageLimit = fragmentAdpater.count
        }
        pager.addOnPageChangeListener(this)
    }

    private val mColor = monthlyColor.getInstance()
    private fun setNavigation() {
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.food -> {
                    pager.setCurrentItem(0, true)
                }
                R.id.schedule -> {
                    pager.setCurrentItem(1, true)
                }
                R.id.calendar -> {
                    pager.setCurrentItem(2, true)
                }
                else -> {
                    pager.setCurrentItem(0, true)
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


    override fun onPageScrollStateChanged(state: Int) {
    }
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
    private var preItem: MenuItem? = null
    override fun onPageSelected(position: Int) {
        if (preItem != null)
            preItem!!.isChecked = false
        else
            bottomNav.menu.getItem(0).isChecked = false

        bottomNav.menu.getItem(position).isChecked = true
        preItem = bottomNav.menu.getItem(position)

        when(position) {
            0 -> { pagerTitle.text = resources.getText(R.string.todaysLunch) }
            1 -> { pagerTitle.text = resources.getText(R.string.classSchedule) }
            2 -> { pagerTitle.text = resources.getText(R.string.monthlySchedule) }
            else -> { pagerTitle.text = resources.getText(R.string.errorString) }
        }
    }

    private fun startRevealActivity(view: View) {
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()

        val i = Intent(this, OptionsActivity::class.java)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                putExtra(EXTRA_CIRCULAR_REVEAL_X, revealX)
                putExtra(EXTRA_CIRCULAR_REVEAL_Y, revealY) }

        ActivityCompat.startActivity(this@MainActivity, i, null)
        overridePendingTransition(0, 0)
    }
}
