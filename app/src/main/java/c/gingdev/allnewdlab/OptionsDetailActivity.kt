package c.gingdev.allnewdlab

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import c.gingdev.allnewdlab.fragments.copyRightFragment
import c.gingdev.allnewdlab.utils.ColorChecker
import kotlinx.android.synthetic.main.activity_options_detail.*


class OptionsDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options_detail)

        initStatusBar()
        initFragment()

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
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
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

    private val fm = supportFragmentManager
    private val transaction = fm.beginTransaction()
    private fun initFragment() {
        transaction.replace(R.id.fragment, copyRightFragment.getInstance()).commit()
    }

    override fun onBackPressed() {
        finish()
    }
}