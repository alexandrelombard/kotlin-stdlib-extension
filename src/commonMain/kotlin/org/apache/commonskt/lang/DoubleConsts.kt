package org.apache.commonskt.lang

import org.apache.commonskt.PublicApi

@PublicApi
@ExperimentalUnsignedTypes
object DoubleConsts {
    const val EXP_BIAS = 1023
    const val MIN_EXPONENT = -1022
    const val MAX_EXPONENT = 1023
    /**
     * Bit mask to isolate the sign bit of a `double`.
     */
    val SIGN_BIT_MASK = 0x8000000000000000UL

    /**
     * Bit mask to isolate the significand field of a `double`.
     */
    const val SIGNIF_BIT_MASK = 0x000FFFFFFFFFFFFFL

    /**
     * The number of logical bits in the significand of a
     * {@code double} number, including the implicit bit.
     */
    const val SIGNIFICAND_WIDTH = 53
    const val SIZE = 64
}