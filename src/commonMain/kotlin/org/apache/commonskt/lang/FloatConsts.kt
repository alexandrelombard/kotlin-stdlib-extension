package org.apache.commonskt.lang

import org.apache.commonskt.PublicApi

@PublicApi
object FloatConsts {
    const val EXP_BIAS = 127
    const val MIN_EXPONENT = -126
    const val MAX_EXPONENT = 127
    const val MIN_NORMAL = 1.17549435E-38f
    const val SIGN_BIT_MASK = -2147483648
    const val SIGNIF_BIT_MASK = 8388607
    const val SIGNIFICAND_WIDTH = 24
}