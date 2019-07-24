package c.gingdev.allnewdlab

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import c.gingdev.allnewdlab.fragments.copyRightFragment
import c.gingdev.allnewdlab.fragments.developerInfoFragment
import c.gingdev.allnewdlab.utils.ColorChecker
import kotlinx.android.synthetic.main.activity_options_detail.*

const val developer = 100
const val copyRight = 200
class OptionsDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options_detail)

        clearStatusBar()
        checkIntentState()
        initFragment()

        Runnable {
            backGroundColorChanged()
        }.run()
    }

    private fun clearStatusBar() {
        window.run {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = 0x00000000

            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
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

    private var state: Int? = null
    private fun checkIntentState() {
        state = intent?.extras?.getInt("state")
    }

    private val fm = supportFragmentManager
    private val transaction = fm.beginTransaction()
    private fun initFragment() {
        when(state) {
            developer -> {
                transaction.replace(R.id.fragment, developerInfoFragment.getInstance()).commit()
            }
            copyRight -> {
                transaction.replace(R.id.fragment, copyRightFragment.getInstance()).commit()
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}