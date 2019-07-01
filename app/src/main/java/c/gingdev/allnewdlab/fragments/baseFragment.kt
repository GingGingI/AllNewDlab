package c.gingdev.allnewdlab.fragments

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import c.gingdev.allnewdlab.R
import com.airbnb.lottie.LottieAnimationView

class baseFragment : baseInterface {
    override lateinit var fadeout: Animation
    override lateinit var lottieAnimationView: LottieAnimationView

    override fun initAnim(context: Context, lottieAnimationView: LottieAnimationView) {
        this.fadeout = AnimationUtils.loadAnimation(context, R.anim.anim_fade_out)
        this.lottieAnimationView = lottieAnimationView
    }

    override fun startLoading() {
        lottieAnimationView
            .apply {
                visibility = View.VISIBLE
                setAnimation("loading.json")
                loop(true)
            }.run {
                playAnimation()
            }
    }

    override fun finishLoading() {
        lottieAnimationView.run {
            startAnimation(fadeout)
            cancelAnimation()
            visibility = View.GONE
        }
    }

}