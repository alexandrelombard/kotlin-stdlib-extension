package org.apache.commonskt.math

import org.apache.commonskt.assert
import org.apache.commonskt.lang.DoubleConsts
import org.apache.commonskt.lang.FloatConsts
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

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

/**
 * Returns `d`
 * 2<sup>`scaleFactor`</sup> rounded as if performed
 * by a single correctly rounded floating-point multiply to a
 * member of the double value set.  See the Java
 * Language Specification for a discussion of floating-point
 * value sets.  If the exponent of the result is between [ ][Double.MIN_EXPONENT] and [Double.MAX_EXPONENT], the
 * answer is calculated exactly.  If the exponent of the result
 * would be larger than `Double.MAX_EXPONENT`, an
 * infinity is returned.  Note that if the result is subnormal,
 * precision may be lost; that is, when `scalb(x, n)`
 * is subnormal, `scalb(scalb(x, n), -n)` may not equal
 * *x*.  When the result is non-NaN, the result has the same
 * sign as `d`.
 *
 *
 * Special cases:
 *
 *  *  If the first argument is NaN, NaN is returned.
 *  *  If the first argument is infinite, then an infinity of the
 * same sign is returned.
 *  *  If the first argument is zero, then a zero of the same
 * sign is returned.
 *
 *
 * @param d number to be scaled by a power of two.
 * @param scaleFactor power of 2 used to scale `d`
 * @return `d`  2<sup>`scaleFactor`</sup>
 * @since 1.6
 */
@ExperimentalUnsignedTypes
fun Double.scalb(scaleFactor: Int): Double {
    /*
     * This method does not need to be declared strictfp to
     * compute the same correct result on all platforms.  When
     * scaling up, it does not matter what order the
     * multiply-store operations are done; the result will be
     * finite or overflow regardless of the operation ordering.
     * However, to get the correct result when scaling down, a
     * particular ordering must be used.
     *
     * When scaling down, the multiply-store operations are
     * sequenced so that it is not possible for two consecutive
     * multiply-stores to return subnormal results.  If one
     * multiply-store result is subnormal, the next multiply will
     * round it away to zero.  This is done by first multiplying
     * by 2 ^ (scaleFactor % n) and then multiplying several
     * times by 2^n as needed where n is the exponent of number
     * that is a covenient power of two.  In this way, at most one
     * real rounding error occurs.  If the double value set is
     * being used exclusively, the rounding will occur on a
     * multiply.  If the double-extended-exponent value set is
     * being used, the products will (perhaps) be exact but the
     * stores to d are guaranteed to round to the double value
     * set.
     *
     * It is _not_ a valid implementation to first multiply d by
     * 2^MIN_EXPONENT and then by 2 ^ (scaleFactor %
     * MIN_EXPONENT) since even in a strictfp program double
     * rounding on underflow could occur; e.g. if the scaleFactor
     * argument was (MIN_EXPONENT - n) and the exponent of d was a
     * little less than -(MIN_EXPONENT - n), meaning the final
     * result would be subnormal.
     *
     * Since exact reproducibility of this method can be achieved
     * without any undue performance burden, there is no
     * compelling reason to allow double rounding on underflow in
     * scalb.
     */

    // magnitude of a power of two so large that scaling a finite
    // nonzero value by it would be guaranteed to over or
    // underflow; due to rounding, scaling down takes an
    // additional power of two which is reflected here
    var d = this
    var scaleFactor = scaleFactor
    val MAX_SCALE: Int = DoubleConsts.MAX_EXPONENT + -DoubleConsts.MIN_EXPONENT +
            DoubleConsts.SIGNIFICAND_WIDTH + 1
    var exp_adjust = 0
    var scale_increment = 0
    var exp_delta = Double.NaN

    // Make sure scaling factor is in a reasonable range
    if (scaleFactor < 0) {
        scaleFactor = max(scaleFactor, -MAX_SCALE)
        scale_increment = -512
        exp_delta = twoToTheDoubleScaleDown
    } else {
        scaleFactor = min(scaleFactor, MAX_SCALE)
        scale_increment = 512
        exp_delta = twoToTheDoubleScaleUp
    }

    // Calculate (scaleFactor % +/-512), 512 = 2^9, using
    // technique from "Hacker's Delight" section 10-2.
    val t = scaleFactor shr 9 - 1 ushr 32 - 9
    exp_adjust = (scaleFactor + t and 512 - 1) - t
    d *= powerOfTwoD(exp_adjust)
    scaleFactor -= exp_adjust
    while (scaleFactor != 0) {
        d *= exp_delta
        scaleFactor -= scale_increment
    }
    return d
}

/**
 * Returns `f`
 * 2<sup>`scaleFactor`</sup> rounded as if performed
 * by a single correctly rounded floating-point multiply to a
 * member of the float value set.  See the Java
 * Language Specification for a discussion of floating-point
 * value sets.  If the exponent of the result is between [ ][Float.MIN_EXPONENT] and [Float.MAX_EXPONENT], the
 * answer is calculated exactly.  If the exponent of the result
 * would be larger than `Float.MAX_EXPONENT`, an
 * infinity is returned.  Note that if the result is subnormal,
 * precision may be lost; that is, when `scalb(x, n)`
 * is subnormal, `scalb(scalb(x, n), -n)` may not equal
 * *x*.  When the result is non-NaN, the result has the same
 * sign as `f`.
 *
 *
 * Special cases:
 *
 *  *  If the first argument is NaN, NaN is returned.
 *  *  If the first argument is infinite, then an infinity of the
 * same sign is returned.
 *  *  If the first argument is zero, then a zero of the same
 * sign is returned.
 *
 *
 * @param f number to be scaled by a power of two.
 * @param scaleFactor power of 2 used to scale `f`
 * @return `f`  2<sup>`scaleFactor`</sup>
 * @since 1.6
 */
@ExperimentalUnsignedTypes
fun Float.scalb(scaleFactor: Int): Float {
    // magnitude of a power of two so large that scaling a finite
    // nonzero value by it would be guaranteed to over or
    // underflow; due to rounding, scaling down takes an
    // additional power of two which is reflected here
    var scaleFactor = scaleFactor
    val MAX_SCALE: Int = FloatConsts.MAX_EXPONENT + -FloatConsts.MIN_EXPONENT +
            FloatConsts.SIGNIFICAND_WIDTH + 1

    // Make sure scaling factor is in a reasonable range
    scaleFactor = max(min(scaleFactor, MAX_SCALE), -MAX_SCALE)

    /*
     * Since + MAX_SCALE for float fits well within the double
     * exponent range and + float -> double conversion is exact
     * the multiplication below will be exact. Therefore, the
     * rounding that occurs when the double product is cast to
     * float will be the correctly rounded float result.  Since
     * all operations other than the final multiply will be exact,
     * it is not necessary to declare this method strictfp.
     */
    return (this.toDouble() * powerOfTwoD(scaleFactor)).toFloat()
}

@ExperimentalUnsignedTypes
var twoToTheDoubleScaleUp = powerOfTwoD(512)
@ExperimentalUnsignedTypes
var twoToTheDoubleScaleDown = powerOfTwoD(-512)

/**
 * Returns a floating-point power of two in the normal range.
 */
@ExperimentalUnsignedTypes
fun powerOfTwoD(n: Int): Double {
    assert(n >= DoubleConsts.MIN_EXPONENT && n <= DoubleConsts.MAX_EXPONENT)
    return Double.fromBits(
        n.toLong() + DoubleConsts.EXP_BIAS.toLong() shl
                DoubleConsts.SIGNIFICAND_WIDTH - 1
                and DoubleConsts.EXP_BIT_MASK
    )
}