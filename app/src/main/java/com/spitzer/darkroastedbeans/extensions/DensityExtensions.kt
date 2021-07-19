package com.spitzer.darkroastedbeans.extensions

import android.content.Context

fun Int.DptoPx(context: Context) = (this * context.resources.displayMetrics.density).toInt()
fun Float.DptoPx(context: Context) = (this * context.resources.displayMetrics.density).toInt()
