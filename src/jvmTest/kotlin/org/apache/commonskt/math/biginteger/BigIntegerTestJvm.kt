package org.apache.commonskt.math.biginteger

import org.apache.commonskt.math.BigInteger
import org.jetbrains.annotations.TestOnly
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Test the commons BigInteger class by comparing its output to the JVM BigInteger implementation
 * @author Alexandre Lombard
 */
@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
class BigIntegerTestJvm {
    @Test
    fun testConstructorString() {
        val bC = BigInteger("123456789")
        val bJvm = java.math.BigInteger("123456789")

        assertEquals(bJvm.toString(), bC.toString())
    }
}