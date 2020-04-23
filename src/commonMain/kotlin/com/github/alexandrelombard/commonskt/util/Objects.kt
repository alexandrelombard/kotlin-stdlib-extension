/*
 * Copyright (c) 2009, 2016, Oracle and/or its affiliates. All rights reserved.
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
package com.github.alexandrelombard.commonskt.util

import com.github.alexandrelombard.commonskt.PublicApi
import com.github.alexandrelombard.commonskt.util.Preconditions.checkFromToIndex

@PublicApi
object Objects {
    /**
     * Checks if the sub-range from `fromIndex` (inclusive) to
     * `toIndex` (exclusive) is within the bounds of range from `0`
     * (inclusive) to `length` (exclusive).
     *
     *
     * The sub-range is defined to be out of bounds if any of the following
     * inequalities is true:
     *
     *  * `fromIndex < 0`
     *  * `fromIndex > toIndex`
     *  * `toIndex > length`
     *  * `length < 0`, which is implied from the former inequalities
     *
     *
     * @param fromIndex the lower-bound (inclusive) of the sub-range
     * @param toIndex the upper-bound (exclusive) of the sub-range
     * @param length the upper-bound (exclusive) the range
     * @return `fromIndex` if the sub-range within bounds of the range
     * @throws IndexOutOfBoundsException if the sub-range is out of bounds
     * @since 9
     */
    fun checkFromToIndex(fromIndex: Int, toIndex: Int, length: Int): Int {
        return checkFromToIndex<RuntimeException>(fromIndex, toIndex, length, null)
    }

    /**
     * Checks if the sub-range from `fromIndex` (inclusive) to
     * `fromIndex + size` (exclusive) is within the bounds of range from
     * `0` (inclusive) to `length` (exclusive).
     *
     *
     * The sub-range is defined to be out of bounds if any of the following
     * inequalities is true:
     *
     *  * `fromIndex < 0`
     *  * `size < 0`
     *  * `fromIndex + size > length`, taking into account integer overflow
     *  * `length < 0`, which is implied from the former inequalities
     *
     *
     * @param fromIndex the lower-bound (inclusive) of the sub-interval
     * @param size the size of the sub-range
     * @param length the upper-bound (exclusive) of the range
     * @return `fromIndex` if the sub-range within bounds of the range
     * @throws IndexOutOfBoundsException if the sub-range is out of bounds
     * @since 9
     */
    fun checkFromIndexSize(fromIndex: Int, size: Int, length: Int): Int {
        return Preconditions.checkFromIndexSize<IndexOutOfBoundsException>(fromIndex, size, length)
    }
}