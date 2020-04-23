package com.github.alexandrelombard.commonskt.lang

import com.github.alexandrelombard.commonskt.PublicApi

@PublicApi
expect object Character {
    val MIN_RADIX: Int
    val MAX_RADIX: Int
    fun digit(ch: Char, radix: Int): Int
    fun isDigit(ch: Char): Boolean
}
