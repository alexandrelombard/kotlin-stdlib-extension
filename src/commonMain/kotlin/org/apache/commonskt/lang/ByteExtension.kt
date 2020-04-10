package org.apache.commonskt.lang

infix fun Byte.shl(bitCount: Int) = (this.toInt() shl bitCount).toByte()
infix fun Byte.shr(bitCount: Int) = (this.toInt() shr bitCount).toByte()
