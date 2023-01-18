package com.ferpa.utils

import com.ferpa.data.model.Photo
import com.ferpa.data.model.resetRank
import com.ferpa.data.model.toRankUpdate
import kotlin.math.roundToInt

fun Long.divideToPercent(divideTo: Long) = ((this.toDouble() / divideTo) * 100).roundToInt() / 100.0

fun List<Photo>.toRankUpdateList() = this.map { it.toRankUpdate() }
