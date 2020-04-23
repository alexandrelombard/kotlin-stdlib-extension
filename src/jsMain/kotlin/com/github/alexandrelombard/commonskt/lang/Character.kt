package com.github.alexandrelombard.commonskt.lang

actual object Character {
    actual val MIN_RADIX: Int = 2
    actual val MAX_RADIX: Int = 36
    actual fun digit(ch: Char, radix: Int): Int {
        return if(radix < MIN_RADIX || radix > MAX_RADIX)
            -1
        else {
            ch.toString().toInt(radix)
        }
    }
    actual fun isDigit(ch: Char): Boolean = ch.toString().toIntOrNull() !== null
}