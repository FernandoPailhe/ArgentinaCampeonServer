package com.ferpa.utils

import kotlin.math.roundToInt

fun Long.divideToPercent(divideTo: Long) = ((this.toDouble() / divideTo) * 100).roundToInt() / 100.0