package com.github.alexandrelombard.commonskt.lang

import com.github.alexandrelombard.commonskt.PublicApi

@PublicApi
object FloatConsts {
    /**
     * Bias used in representing a `float` exponent.
     */
    const val EXP_BIAS = 127
    const val MIN_EXPONENT = -126
    const val MAX_EXPONENT = 127
    const val MIN_NORMAL = 1.17549435E-38f
    const val SIGN_BIT_MASK = -2147483648

    /**
     * The number of logical bits in the significand of a
     * `float` number, including the implicit bit.
     */
    const val SIGNIFICAND_WIDTH = 24

    /**
     * Bit mask to isolate the exponent field of a `float`.
     */
    const val EXP_BIT_MASK = 0x7F800000

    /**
     * Bit mask to isolate the significand field of a `float`.
     */
    const val SIGNIF_BIT_MASK = 0x007FFFFF
}