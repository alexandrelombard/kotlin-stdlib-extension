package org.apache.commonskt.lang

import org.apache.commonskt.PublicApi

@PublicApi
expect object Character {
    val MIN_RADIX: Int
    val MAX_RADIX: Int
    fun digit(ch: Char, radix: Int): Int
    fun isDigit(ch: Char): Boolean
}
