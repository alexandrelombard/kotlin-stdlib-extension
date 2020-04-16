package org.apache.commonskt.collections

import kotlin.test.Test
import kotlin.test.assertEquals

class ArrayExtensionTest {
    @Test
    fun testCopyOf() {
        val array = Array(100) { it }
        val copy = array.copyOf(200) { i, _ -> i }
        assertEquals(200, copy.size)
        assertEquals(101, copy[101])
        assertEquals(199, copy[199])
    }

    @Test
    fun testCopyOfWithAccess() {
        val array = Array(100) { it }
        val copy = array.copyOf(200) { i, arr -> arr[i - 1] + i }
        assertEquals(200, copy.size)
        assertEquals(199, copy[100])
        assertEquals(15049, copy[199])
    }
}