package com.pranala.test.app.extensions

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

fun Int.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).roundToInt().dp
}

fun Int.toSp() = (this / Resources.getSystem().displayMetrics.density).roundToInt().sp