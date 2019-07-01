package c.gingdev.allnewdlab.fragments

import android.content.Context
import android.view.animation.Animation
import com.airbnb.lottie.LottieAnimationView

interface baseInterface {
    var lottieAnimationView: LottieAnimationView
    var fadeout: Animation

    fun initAnim(context: Context, lottieAnimationView: LottieAnimationView)
    fun startLoading()
    fun finishLoading()
}