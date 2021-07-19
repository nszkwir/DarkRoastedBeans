package com.spitzer.darkroastedbeans.extensions

import android.animation.ObjectAnimator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


fun LinearLayout.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val params = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(left, top, right, bottom)
    this.layoutParams = params
    requestLayout()
}

fun TextView.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val params = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(left, top, right, bottom)
    this.layoutParams = params
    requestLayout()
}

fun TextView.moveLeft(dp: Int) {
    ObjectAnimator.ofFloat(
        this,
        "translationX",
        dp.DptoPx(context).toFloat()
    ).apply {
        duration = 1
        start()
    }
}