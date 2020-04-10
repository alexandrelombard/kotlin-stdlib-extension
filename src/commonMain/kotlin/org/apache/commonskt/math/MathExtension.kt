package org.apache.commonskt.math

import kotlin.math.abs

/**
 * Returns the sum of its arguments,
 * throwing an exception if the result overflows an `int`.
 *
 * @param x the first value
 * @param y the second value
 * @return the result
 * @throws ArithmeticException if the result overflows an int
 * @since 1.8
 */
fun addExact(x: Int, y: Int): Int {
    val r = x + y
    // HD 2-12 Overflow iff both arguments have the opposite sign of the result
    if (x xor r and (y xor r) < 0) {
        throw ArithmeticException("integer overflow")
    }
    return r
}

/**
 * Returns the sum of its arguments,
 * throwing an exception if the result overflows a `long`.
 *
 * @param x the first value
 * @param y the second value
 * @return the result
 * @throws ArithmeticException if the result overflows a long
 * @since 1.8
 */
fun addExact(x: Long, y: Long): Long {
    val r = x + y
    // HD 2-12 Overflow iff both arguments have the opposite sign of the result
    if (x xor r and (y xor r) < 0) {
        throw ArithmeticException("long overflow")
    }
    return r
}

/**
 * Returns the difference of the arguments,
 * throwing an exception if the result overflows an `int`.
 *
 * @param x the first value
 * @param y the second value to subtract from the first
 * @return the result
 * @throws ArithmeticException if the result overflows an int
 * @since 1.8
 */
fun subtractExact(x: Int, y: Int): Int {
    val r = x - y
    // HD 2-12 Overflow iff the arguments have different signs and
    // the sign of the result is different than the sign of x
    if (x xor y and (x xor r) < 0) {
        throw ArithmeticException("integer overflow")
    }
    return r
}

/**
 * Returns the difference of the arguments,
 * throwing an exception if the result overflows a `long`.
 *
 * @param x the first value
 * @param y the second value to subtract from the first
 * @return the result
 * @throws ArithmeticException if the result overflows a long
 * @since 1.8
 */
fun subtractExact(x: Long, y: Long): Long {
    val r = x - y
    // HD 2-12 Overflow iff the arguments have different signs and
    // the sign of the result is different than the sign of x
    if (x xor y and (x xor r) < 0) {
        throw ArithmeticException("long overflow")
    }
    return r
}

/**
 * Returns the product of the arguments,
 * throwing an exception if the result overflows an {@code int}.
 *
 * @param x the first value
 * @param y the second value
 * @return the result
 * @throws ArithmeticException if the result overflows an int
 * @since 1.8
 */
fun multiplyExact(x: Int, y: Int): Int {
    val r = x.toLong() * y.toLong()
    if (r.toInt().toLong() != r){
        throw ArithmeticException("integer overflow")
    }
    return r.toInt()
}

/**
 * Returns the product of the arguments,
 * throwing an exception if the result overflows a `long`.
 *
 * @param x the first value
 * @param y the second value
 * @return the result
 * @throws ArithmeticException if the result overflows a long
 * @since 1.8
 */
fun multiplyExact(x: Long, y: Long): Long {
    val r = x * y
    val ax: Long = abs(x)
    val ay: Long = abs(y)
    if (ax or ay ushr 31 != 0L) {
        // Some bits greater than 2^31 that might cause overflow
        // Check the result using the divide operator
        // and check for the special case of Long.MIN_VALUE * -1
        if (y != 0L && r / y != x ||
            x == Long.MIN_VALUE && y == -1L
        ) {
            throw ArithmeticException("long overflow")
        }
    }
    return r
}


/**
 * Returns the argument incremented by one, throwing an exception if the
 * result overflows an `int`.
 *
 * @param a the value to increment
 * @return the result
 * @throws ArithmeticException if the result overflows an int
 * @since 1.8
 */
fun incrementExact(a: Int): Int {
    if (a == Int.MAX_VALUE) {
        throw ArithmeticException("integer overflow")
    }
    return a + 1
}

/**
 * Returns the argument incremented by one, throwing an exception if the
 * result overflows a `long`.
 *
 * @param a the value to increment
 * @return the result
 * @throws ArithmeticException if the result overflows a long
 * @since 1.8
 */
fun incrementExact(a: Long): Long {
    if (a == Long.MAX_VALUE) {
        throw ArithmeticException("long overflow")
    }
    return a + 1L
}

/**
 * Returns the argument decremented by one, throwing an exception if the
 * result overflows an `int`.
 *
 * @param a the value to decrement
 * @return the result
 * @throws ArithmeticException if the result overflows an int
 * @since 1.8
 */
fun decrementExact(a: Int): Int {
    if (a == Int.MIN_VALUE) {
        throw ArithmeticException("integer overflow")
    }
    return a - 1
}

/**
 * Returns the argument decremented by one, throwing an exception if the
 * result overflows a `long`.
 *
 * @param a the value to decrement
 * @return the result
 * @throws ArithmeticException if the result overflows a long
 * @since 1.8
 */
fun decrementExact(a: Long): Long {
    if (a == Long.MIN_VALUE) {
        throw ArithmeticException("long overflow")
    }
    return a - 1L
}

/**
 * Returns the negation of the argument, throwing an exception if the
 * result overflows an `int`.
 *
 * @param a the value to negate
 * @return the result
 * @throws ArithmeticException if the result overflows an int
 * @since 1.8
 */
fun negateExact(a: Int): Int {
    if (a == Int.MIN_VALUE) {
        throw ArithmeticException("integer overflow")
    }
    return -a
}

/**
 * Returns the negation of the argument, throwing an exception if the
 * result overflows a `long`.
 *
 * @param a the value to negate
 * @return the result
 * @throws ArithmeticException if the result overflows a long
 * @since 1.8
 */
fun negateExact(a: Long): Long {
    if (a == Long.MIN_VALUE) {
        throw ArithmeticException("long overflow")
    }
    return -a
}

/**
 * Returns the value of the `long` argument;
 * throwing an exception if the value overflows an `int`.
 *
 * @param value the long value
 * @return the argument as an int
 * @throws ArithmeticException if the `argument` overflows an int
 * @since 1.8
 */
fun toIntExact(value: Long): Int {
    if (value.toInt().toLong() != value){
        throw ArithmeticException("integer overflow")
    }
    return value.toInt()
}