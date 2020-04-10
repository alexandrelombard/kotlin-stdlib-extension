package org.apache.commonskt.lang

import org.apache.commonskt.PublicApi

@PublicApi
@ExperimentalUnsignedTypes
object DoubleConsts {
    const val EXP_BIAS = 1023
    const val MIN_EXPONENT = -1022
    const val MAX_EXPONENT = 1023
    const val SIGN_BIT_MASK = 0x8000000000000000UL
    const val SIGNIF_BIT_MASK = 4503599627370495L
    const val SIGNIFICAND_WIDTH = 53
    const val SIZE = 64
}