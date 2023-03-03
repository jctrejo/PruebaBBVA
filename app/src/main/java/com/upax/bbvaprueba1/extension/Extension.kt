package com.upax.bbvaprueba1.extension

import android.view.View
import com.airbnb.lottie.LottieAnimationView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun LottieAnimationView.showLoader() {
    this.playAnimation()
    this.show()
}

fun LottieAnimationView.hideLoader() {
    this.hide()
    this.cancelAnimation()
}