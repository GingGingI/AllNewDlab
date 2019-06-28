package c.gingdev.allnewdlab.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator

const val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
const val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
class revealAnimation {

    private val view: View
    private val activity: Activity

    private var revealX: Int = 0
    private var revealY: Int = 0

    constructor(view: View, activity: Activity, intent: Intent) {
        this.view = view
        this.activity = activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            view.visibility = View.INVISIBLE

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)

            val vtObserver = view.viewTreeObserver
            if (vtObserver.isAlive)
                vtObserver.addOnGlobalLayoutListener {
                    revalActivity(revealX, revealY)
                }
        } else {
            view.visibility = View.VISIBLE
        }
    }

    fun revalActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(view.width, view.height) * 1.1).toFloat()

            ViewAnimationUtils.createCircularReveal(view, x, y, 0f, finalRadius)
                .apply {
                    duration = 300
                    interpolator = AccelerateInterpolator() }
                .run {
                    view.visibility = View.VISIBLE
                    start() }
        } else {
            activity.finish()
        }
    }

    fun unRevealActivity() {
        val finalRadius = (Math.max(view.width, view.height) * 1.1).toFloat()

        ViewAnimationUtils.createCircularReveal(view, revealX, revealY, finalRadius, 0f)
            .apply {
                duration = 300
                addListener(object: AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        view.visibility = View.INVISIBLE
                        activity.finish()
                        activity.overridePendingTransition(0, 0)
                    }
                }) }
            .run { start() }
    }
}