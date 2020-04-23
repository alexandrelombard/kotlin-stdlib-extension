/*
 * Copyright (c) 1999, 2007, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
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
package com.github.alexandrelombard.commonskt.math

import kotlin.random.Random

/**
 * A simple bit sieve used for finding prime number candidates. Allows setting
 * and clearing of bits in a storage array. The size of the sieve is assumed to
 * be constant to reduce overhead. All the bits of a new bitSieve are zero, and
 * bits are removed from it by setting them.
 *
 * To reduce storage space and increase efficiency, no even numbers are
 * represented in the sieve (each bit in the sieve represents an odd number).
 * The relationship between the index of a bit and the number it represents is
 * given by
 * N = offset + (2*index + 1);
 * Where N is the integer represented by a bit in the sieve, offset is some
 * even integer offset indicating where the sieve begins, and index is the
 * index of a bit in the sieve array.
 *
 * @see BigInteger
 *
 * @author  Michael McCloskey
 * @since   1.3
 */
internal class BitSieve {
    /**
     * Stores the bits in this bitSieve.
     */
    private var bits: LongArray

    /**
     * Length is how many bits this sieve holds.
     */
    private var length: Int

    /**
     * Construct a "small sieve" with a base of 0.  This constructor is
     * used internally to generate the set of "small primes" whose multiples
     * are excluded from sieves generated by the main (package private)
     * constructor, BitSieve(BigInteger base, int searchLen).  The length
     * of the sieve generated by this constructor was chosen for performance;
     * it controls a tradeoff between how much time is spent constructing
     * other sieves, and how much time is wasted testing composite candidates
     * for primality.  The length was chosen experimentally to yield good
     * performance.
     */
    private constructor() {
        length = 150 * 64
        bits = LongArray(unitIndex(length - 1) + 1)

        // Mark 1 as composite
        set(0)
        var nextIndex = 1
        var nextPrime = 3

        // Find primes and remove their multiples from sieve
        do {
            sieveSingle(length, nextIndex + nextPrime, nextPrime)
            nextIndex = sieveSearch(length, nextIndex + 1)
            nextPrime = 2 * nextIndex + 1
        } while (nextIndex > 0 && nextPrime < length)
    }

    /**
     * Construct a bit sieve of searchLen bits used for finding prime number
     * candidates. The new sieve begins at the specified base, which must
     * be even.
     */
    @ExperimentalStdlibApi
    constructor(base: BigInteger, searchLen: Int) {
        /*
         * Candidates are indicated by clear bits in the sieve. As a candidates
         * nonprimality is calculated, a bit is set in the sieve to eliminate
         * it. To reduce storage space and increase efficiency, no even numbers
         * are represented in the sieve (each bit in the sieve represents an
         * odd number).
         */
        bits = LongArray(unitIndex(searchLen - 1) + 1)
        length = searchLen
        var start = 0
        var step =
            smallSieve.sieveSearch(smallSieve.length, start)
        var convertedStep = step * 2 + 1

        // Construct the large sieve at an even offset specified by base
        val b = MutableBigInteger(base)
        val q = MutableBigInteger()
        do {
            // Calculate base mod convertedStep
            start = b.divideOneWord(convertedStep, q)

            // Take each multiple of step out of sieve
            start = convertedStep - start
            if (start % 2 == 0) start += convertedStep
            sieveSingle(searchLen, (start - 1) / 2, convertedStep)

            // Find next prime from small sieve
            step = smallSieve.sieveSearch(
                smallSieve.length,
                step + 1
            )
            convertedStep = step * 2 + 1
        } while (step > 0)
    }

    /**
     * Get the value of the bit at the specified index.
     */
    private operator fun get(bitIndex: Int): Boolean {
        val unitIndex = unitIndex(bitIndex)
        return (bits[unitIndex] and bit(bitIndex)) != 0L
    }

    /**
     * Set the bit at the specified index.
     */
    private fun set(bitIndex: Int) {
        val unitIndex = unitIndex(bitIndex)
        bits[unitIndex] = (bits[unitIndex] or bit(bitIndex))
    }

    /**
     * This method returns the index of the first clear bit in the search
     * array that occurs at or after start. It will not search past the
     * specified limit. It returns -1 if there is no such clear bit.
     */
    private fun sieveSearch(limit: Int, start: Int): Int {
        if (start >= limit) return -1
        var index = start
        do {
            if (!get(index)) return index
            index++
        } while (index < limit - 1)
        return -1
    }

    /**
     * Sieve a single set of multiples out of the sieve. Begin to remove
     * multiples of the specified step starting at the specified start index,
     * up to the specified limit.
     */
    private fun sieveSingle(limit: Int, start: Int, step: Int) {
        var localStart = start
        while (localStart < limit) {
            set(localStart)
            localStart += step
        }
    }

    /**
     * Test probable primes in the sieve and return successful candidates.
     */
    @ExperimentalStdlibApi
    fun retrieve(initValue: BigInteger, certainty: Int, random: Random?): BigInteger? {
        // Examine the sieve one long at a time to find possible primes
        var offset = 1
        for (i in bits.indices) {
            var nextLong = bits[i].inv()
            for (j in 0..63) {
                if ((nextLong and 1) == 1L) {
                    val candidate: BigInteger = initValue.add(
                        BigInteger.valueOf(offset.toLong())
                    )
                    if (candidate.primeToCertainty(certainty, random)) return candidate
                }
                nextLong = nextLong ushr 1
                offset += 2
            }
        }
        return null
    }

    companion object {
        /**
         * A small sieve used to filter out multiples of small primes in a search
         * sieve.
         */
        private val smallSieve = BitSieve()

        /**
         * Given a bit index return unit index containing it.
         */
        private fun unitIndex(bitIndex: Int): Int {
            return bitIndex ushr 6
        }

        /**
         * Return a unit that masks the specified bit in its unit.
         */
        private fun bit(bitIndex: Int): Long {
            return 1L shl (bitIndex and ((1 shl 6) - 1))
        }
    }
}