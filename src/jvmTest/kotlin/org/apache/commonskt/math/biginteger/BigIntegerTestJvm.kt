package org.apache.commonskt.math.biginteger

import org.apache.commonskt.math.BigInteger
import org.junit.Test
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.math.min
import kotlin.random.Random
import kotlin.test.assertEquals

/**
 * Test the commons BigInteger class by comparing its output to the JVM BigInteger implementation
 * @author Alexandre Lombard
 */
@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
class BigIntegerTestJvm {

    private val random = Random.Default

    @Test
    fun testConstructorString() {
        val bC = BigInteger("123456789")
        val bJvm = java.math.BigInteger("123456789")

        assertEquals(bJvm.toString(), bC.toString())
    }

    @Test
    fun testAdd() {
        for(i in 0..SIZE) {
            val b1c = fetchNumber(ORDER_KARATSUBA)
            val b2c = fetchNumber(ORDER_KARATSUBA)
            val b1jvm = java.math.BigInteger(b1c.toString())
            val b2jvm = java.math.BigInteger(b2c.toString())

            assertEquals(b1jvm.add(b2jvm).toString(), b1c.add(b2c).toString())
        }
    }

    @Test
    fun testMultiply() {
        for(i in 0..SIZE) {
            val b1c = fetchNumber(ORDER_KARATSUBA)
            val b2c = fetchNumber(ORDER_KARATSUBA)
            val b1jvm = java.math.BigInteger(b1c.toString())
            val b2jvm = java.math.BigInteger(b2c.toString())

            assertEquals(b1jvm.multiply(b2jvm).toString(), b1c.multiply(b2c).toString())
        }
    }

    @Test
    fun testPow() {
        for(i in 0..SIZE) {
            try {
                val bc = fetchNumber(ORDER_KARATSUBA)
                val bjvm = java.math.BigInteger(bc.toString())
                val rInt = random.nextInt()

                assertEquals(bjvm.pow(rInt).toString(), bc.pow(rInt).toString())
            } catch (e: ArithmeticException) {
                // Exponent is negative
            }
        }
    }

    @Test
    fun testDivide() {
        for(i in 0..SIZE) {
            try {
                val b1c = fetchNumber(ORDER_KARATSUBA)
                val b2c = fetchNumber(ORDER_KARATSUBA)
                val b1jvm = java.math.BigInteger(b1c.toString())
                val b2jvm = java.math.BigInteger(b2c.toString())

                assertEquals(b1jvm.divide(b2jvm).toString(), b1c.divide(b2c).toString())
            } catch (e: ArithmeticException) {
                // Divisor is equal to zero
            }
        }
    }

    @Test
    fun testMod() {
        for(i in 0..SIZE) {
            try {
                val b1c = fetchNumber(ORDER_KARATSUBA)
                val b2c = fetchNumber(ORDER_KARATSUBA)
                val b1jvm = java.math.BigInteger(b1c.toString())
                val b2jvm = java.math.BigInteger(b2c.toString())

                assertEquals(b1jvm.mod(b2jvm).toString(), b1c.mod(b2c).toString())
            } catch (e: ArithmeticException) {
                // Divisor is equal to zero
            }
        }
    }

    /*
     * Get a random or boundary-case number. This is designed to provide
     * a lot of numbers that will find failure points, such as max sized
     * numbers, empty BigIntegers, etc.
     *
     * If order is less than 2, order is changed to 2.
     * @param order the order
     */
    private fun fetchNumber(order: Int): BigInteger {
        var localOrder = order
        val negative: Boolean = random.nextBoolean()
        val numType: Int = random.nextInt(7)
        var result: BigInteger?
        if (localOrder < 2) localOrder = 2
        when (numType) {
            0 -> result = BigInteger.ZERO
            1 -> result = BigInteger.ONE
            2 -> {
                val numBytes = (localOrder + 7) / 8
                val fullBits = ByteArray(numBytes)
                var i = 0
                while (i < numBytes) {
                    fullBits[i] = 0xff.toByte()
                    i++
                }
                val excessBits = 8 * numBytes - localOrder
                fullBits[0] = fullBits[0] and ((1 shl 8 - excessBits) - 1).toByte()
                result = BigInteger(1, fullBits)
            }
            3 -> result = BigInteger.ONE.shiftLeft(random.nextInt(localOrder))
            4 -> {
                val `val` = ByteArray((localOrder + 7) / 8)
                val iterations: Int = random.nextInt(localOrder)
                var i = 0
                while (i < iterations) {
                    val bitIdx: Int = random.nextInt(localOrder)
                    `val`[bitIdx / 8] = `val`[bitIdx / 8] or (1 shl (bitIdx % 8)).toByte()
                    i++
                }
                if(`val`[0] == 0.toByte()) `val`[0]++
                result = BigInteger(1, `val`)
            }
            5 -> {
                var localResult = BigInteger.ZERO
                var remaining = localOrder
                var bit = random.nextInt(2)
                while (remaining > 0) {
                    val runLength = min(remaining, random.nextInt(localOrder))
                    localResult = localResult.shiftLeft(runLength)
                    if (bit > 0)
                        localResult = localResult.add(BigInteger.ONE.shiftLeft(runLength).subtract(BigInteger.ONE))
                    remaining -= runLength
                    bit = 1 - bit
                }
                result = localResult
            }
            else -> result = BigInteger(localOrder, random)
        }
        if (negative) result = result.negate()
        return result
    }
}