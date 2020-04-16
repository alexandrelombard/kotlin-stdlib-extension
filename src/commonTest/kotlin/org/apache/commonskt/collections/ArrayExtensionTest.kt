package org.apache.commonskt.collections

import kotlin.test.Test
import kotlin.test.assertEquals

class ArrayExtensionTest {
    @Test
    fun testCopyOf() {
        val arr = Array(100) { it }
        val copy = arr.copyOf(200) { it }
        assertEquals(200, copy.size)
        assertEquals(101, copy[101])
        assertEquals(199, copy[199])
    }
}