package com.github.alexandrelombard.commonskt.lang

/**
 * Bitwise left shift on a byte
 * @param bitCount the number of bits to shift to the left
 */
infix fun Byte.shl(bitCount: Int) = (this.toInt() shl bitCount).toByte()

/**
 * Bitwise right shift on a byte
 * @param bitCount the number of bits to shift to the right
 */
infix fun Byte.shr(bitCount: Int) = (this.toInt() shr bitCount).toByte()
