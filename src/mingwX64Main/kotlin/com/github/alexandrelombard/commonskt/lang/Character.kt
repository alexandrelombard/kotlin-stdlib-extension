package com.github.alexandrelombard.commonskt.lang

actual object Character {
    actual val MIN_RADIX: Int = Char.MIN_RADIX
    actual val MAX_RADIX: Int = Char.MAX_RADIX
    actual fun digit(ch: Char, radix: Int): Int {
        return if(radix < MIN_RADIX || radix > MAX_RADIX)
            -1
        else {
            ch.toString().toInt(radix)
        }
    }
    actual fun isDigit(ch: Char): Boolean = ch.isDigit()
}