package c.gingdev.allnewdlab

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import c.gingdev.allnewdlab.utils.ColorChecker
import c.gingdev.allnewdlab.utils.preferenceUtil
import kotlinx.android.synthetic.main.activity_grade.*

class GradeActivity: AppCompatActivity() {

    private val pref = preferenceUtil.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        initStatusBar()

        checkFromSetting()
        setLayout()

        Runnable {
            backGroundColorChanged()
        }.run()
    }

    private var isSetting: Boolean = false
    private fun checkFromSetting() {
        isSetting = intent?.extras?.getBoolean("isSetting", false) ?: false
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

    private fun setLayout() {
        setPickers()
        doneBtn.setOnClickListener {
            if (dataNotNull())
                Toast.makeText(applicationContext, "앱재시작 후 적용됩니다.", Toast.LENGTH_LONG).show()
            onItemPicked()
        }
    }
    private fun setPickers() {
        Grade.apply {
            minValue = 1
            maxValue = 3
            value = pref.PREF_GRADE?.toInt() ?: 2 }

        Class.apply {
            minValue = 1
            maxValue = 6
            value = pref.PREF_CLASS?.toInt() ?: 1 }
    }

    private fun onItemPicked() {
        pref.run {
            PREF_GRADE = Grade.value.toString()
            PREF_CLASS = Class.value.toString()
        }
        onfinish()
    }

    private fun onfinish() {
        if (!isSetting && dataNotNull())
            startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun dataNotNull(): Boolean {
        if (!pref.PREF_GRADE.isNullOrEmpty() && !pref.PREF_CLASS.isNullOrEmpty())
            return true
        return false
    }

    override fun onBackPressed() {
        onfinish()
    }
}