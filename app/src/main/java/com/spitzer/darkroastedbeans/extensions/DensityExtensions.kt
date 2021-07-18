package com.spitzer.darkroastedbeans.extensions

import android.content.Context

fun Int.toDp(context: Context) = (this * context.resources.displayMetrics.density).toInt()
fun Float.toDp(context: Context) = (this * context.resources.displayMetrics.density).toInt()
