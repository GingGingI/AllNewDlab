package c.gingdev.allnewdlab

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import c.gingdev.allnewdlab.recycler.adapter.optionAdapter
import c.gingdev.allnewdlab.utils.ColorChecker
import c.gingdev.allnewdlab.utils.revealAnimation
import kotlinx.android.synthetic.main.activity_options.*
import kotlinx.android.synthetic.main.activity_options.recycler

const val grade = 100
const val openSource = 200
class OptionsActivity: AppCompatActivity() {

    lateinit var revealActivity: revealAnimation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        revealActivity = revealAnimation(parentView, this, intent)
        initStatusBar()
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

        cancel.setColorFilter(color)
    }

    private lateinit var adapter: optionAdapter
    private fun initLayout() {
        val arrays = ArrayList<Pair<Int, String>>()
        arrays.add(Pair(grade, resources.getString(R.string.setGrade)))
        arrays.add(Pair(openSource, resources.getString(R.string.openSource)))

        adapter = optionAdapter(arrays).also {
            recycler.adapter = it
            recycler.layoutManager = LinearLayoutManager(applicationContext)

            it.notifyDataSetChanged()
        }

        cancel.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        revealActivity.unRevealActivity()
    }
}