package org.apache.commonskt.lang

actual object Character {
    actual val MIN_RADIX: Int = java.lang.Character.MIN_RADIX
    actual val MAX_RADIX: Int = java.lang.Character.MAX_RADIX
    actual fun digit(ch: Char, radix: Int): Int = java.lang.Character.digit(ch, radix)
    actual fun isDigit(ch: Char): Boolean = java.lang.Character.isDigit(ch)
}