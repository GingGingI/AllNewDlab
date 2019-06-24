package c.gingdev.allnewdlab

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import c.gingdev.allnewdlab.fragments.calendarFragment
import c.gingdev.allnewdlab.fragments.foodFragment
import c.gingdev.allnewdlab.utils.ColorChecker
import c.gingdev.allnewdlab.utils.fragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearStatusBar()
        setFragment()

        Runnable {
            backGroundColorChanged()
        }.run()
    }

    private fun clearStatusBar() {
        window.apply {
            setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    private fun backGroundColorChanged() {
        if (ColorChecker(parentView).isLight()) {
//            현재 어두움.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
//            현제 밝음

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                window.decorView.systemUiVisibility = 0
        }
    }

    private lateinit var adapter: fragmentPagerAdapter
    private val fmList = arrayListOf<Fragment>()
    private fun setFragment() {
        adapter = fragmentPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addItem(foodFragment.getInstance())
        adapter.addItem(calendarFragment.getInstance())

        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.adapter = adapter
        pager.setPageTransformer(MarginPageTransformer(1500))

        val tabLayoutMediator =
            TabLayoutMediator(tabs, pager, true, TabLayoutMediator.OnConfigureTabCallback { tab, position ->
            tab.text = when(position) {0 -> {"급식"} 1 -> {"달력"} else -> {"테스트"} }
        }).attach()
    }


}
