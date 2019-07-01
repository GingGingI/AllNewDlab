package c.gingdev.allnewdlab

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import c.gingdev.allnewdlab.utils.preferenceUtil

class SplashActivity: AppCompatActivity() {

    private val pref = preferenceUtil.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        clearStatusBar()
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            Log.e("Check", pref.PREF_GRADE.isNullOrEmpty().toString())
            Log.e("Check", pref.PREF_CLASS.isNullOrEmpty().toString())
            if (!pref.PREF_GRADE.isNullOrEmpty() && !pref.PREF_CLASS.isNullOrEmpty())
                Intent(this, MainActivity::class.java)
                    .run {
                        startActivity(this)
                        finish() }
            else
                Intent(this, GradeActivity::class.java)
                    .run {
                        startActivity(this)
                        finish() }
        }, 2000)
    }
    private fun clearStatusBar() {
        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}