package c.gingdev.allnewdlab

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import c.gingdev.allnewdlab.recycler.adapter.optionAdapter
import c.gingdev.allnewdlab.utils.ColorChecker
import c.gingdev.allnewdlab.utils.revealAnimation
import kotlinx.android.synthetic.main.activity_options.*
import kotlinx.android.synthetic.main.activity_options.recycler

const val grade = 100
const val createBy = 200
const val openSource = 1000
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
    }
    private fun clearStatusBar() {
        window.run {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
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
        arrays.add(Pair(createBy, resources.getString(R.string.createBy)))
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