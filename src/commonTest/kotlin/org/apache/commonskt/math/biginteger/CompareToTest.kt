/*
 * Copyright (c) 2006, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.apache.commonskt.math.biginteger

import org.apache.commonskt.io.eprintln
import org.apache.commonskt.math.BigInteger
import org.apache.commonskt.math.BigInteger.Companion.ONE
import org.apache.commonskt.math.BigInteger.Companion.ZERO
import org.apache.commonskt.math.BigInteger.Companion.valueOf
import kotlin.test.Test

/*
 * @test
 * @bug 6473768
 * @summary Tests of BigInteger.compareTo
 * @author Joseph D. Darcy
 */
@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
class CompareToTests {
    private fun compareToTests(): Int {
        var failures = 0
        val MINUS_ONE: BigInteger = ONE.negate()
        val TWO_POW_126: BigInteger = ONE.shiftLeft(126)
        val TWO_POW_127: BigInteger = ONE.shiftLeft(127)
        val TWO_POW_128: BigInteger = ONE.shiftLeft(128)

        // First operand, second operand, expected compareTo result
        val testCases: Array<Array<BigInteger>> = arrayOf(
            arrayOf(valueOf(0), valueOf(0), ZERO),
            arrayOf(valueOf(0), valueOf(1), MINUS_ONE),
            arrayOf(valueOf(1), valueOf(2), MINUS_ONE),
            arrayOf(valueOf(2), valueOf(1), ONE),
            arrayOf(valueOf(10), valueOf(10), ZERO),
            arrayOf(TWO_POW_127, TWO_POW_127, ZERO),
            arrayOf(TWO_POW_127.negate(), TWO_POW_127, MINUS_ONE),
            arrayOf(TWO_POW_128.or(TWO_POW_126), TWO_POW_128, ONE),
            arrayOf(TWO_POW_128.or(TWO_POW_126), TWO_POW_128.negate(), ONE),
            arrayOf(TWO_POW_128, TWO_POW_128.or(TWO_POW_126), MINUS_ONE),
            arrayOf(TWO_POW_128.negate(), TWO_POW_128.or(TWO_POW_126), MINUS_ONE),
            arrayOf(TWO_POW_127, TWO_POW_128, MINUS_ONE),
            arrayOf(TWO_POW_127.negate(), TWO_POW_128, MINUS_ONE),
            arrayOf(TWO_POW_128, TWO_POW_127, ONE),
            arrayOf(TWO_POW_128.negate(), TWO_POW_127, MINUS_ONE),
            arrayOf(valueOf(Long.MAX_VALUE), valueOf(Long.MAX_VALUE), ZERO),
            arrayOf(
                valueOf(Long.MAX_VALUE).negate(),
                valueOf(Long.MAX_VALUE),
                MINUS_ONE
            ),
            arrayOf(valueOf(Long.MAX_VALUE - 1), valueOf(Long.MAX_VALUE), MINUS_ONE),
            arrayOf(
                valueOf(Long.MAX_VALUE - 1).negate(),
                valueOf(Long.MAX_VALUE),
                MINUS_ONE
            ),
            arrayOf(valueOf(Long.MIN_VALUE), valueOf(Long.MAX_VALUE), MINUS_ONE),
            arrayOf(valueOf(Long.MIN_VALUE).negate(), valueOf(Long.MAX_VALUE), ONE),
            arrayOf(valueOf(Long.MIN_VALUE + 1), valueOf(Long.MAX_VALUE), MINUS_ONE),
            arrayOf(
                valueOf(Long.MIN_VALUE + 1).negate(),
                valueOf(Long.MAX_VALUE),
                ZERO
            ),
            arrayOf(valueOf(Long.MAX_VALUE), valueOf(Long.MIN_VALUE), ONE),
            arrayOf(valueOf(Long.MAX_VALUE).negate(), valueOf(Long.MIN_VALUE), ONE),
            arrayOf(valueOf(Long.MAX_VALUE - 1), valueOf(Long.MIN_VALUE), ONE),
            arrayOf(valueOf(Long.MAX_VALUE - 1).negate(), valueOf(Long.MIN_VALUE), ONE),
            arrayOf(valueOf(Long.MIN_VALUE), valueOf(Long.MIN_VALUE), ZERO),
            arrayOf(valueOf(Long.MIN_VALUE).negate(), valueOf(Long.MIN_VALUE), ONE),
            arrayOf(valueOf(Long.MIN_VALUE + 1), valueOf(Long.MIN_VALUE), ONE),
            arrayOf(valueOf(Long.MIN_VALUE + 1).negate(), valueOf(Long.MIN_VALUE), ONE)
        )
        for (testCase in testCases) {
            val a: BigInteger = testCase[0]
            val aNegate: BigInteger = a.negate()
            val b: BigInteger = testCase[1]
            val bNegate: BigInteger = b.negate()
            val expected: Int = testCase[2].toInt()
            failures += compareToTest(a, b, expected)
            failures += compareToTest(aNegate, bNegate, -expected)
        }
        return failures
    }

    private fun compareToTest(a: BigInteger, b: BigInteger, expected: Int): Int {
        val result: Int = a.compareTo(b)
        val failed = if (result == expected) 0 else 1
        if (failed == 1) {
            eprintln(
                    """
                        ($a).compareTo($b) => $result
                        Expected $expected
                        """
            )
        }
        return failed
    }

    @Test
    fun main() {
        var failures = 0
        failures += compareToTests()
        if (failures > 0) {
            throw RuntimeException(
                "Incurred " + failures +
                        " failures while testing exact compareTo."
            )
        }
    }
}