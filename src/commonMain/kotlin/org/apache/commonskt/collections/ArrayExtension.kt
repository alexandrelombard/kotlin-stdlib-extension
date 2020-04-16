package org.apache.commonskt.collections

/**
 * Returns new array which is a copy of the original array, resized to the given [newSize].
 * The copy is either truncated or padded at the end with `null` values if necessary.
 *
 * - If [newSize] is less than the size of the original array, the copy array is truncated to the [newSize].
 * - If [newSize] is greater than the size of the original array, the extra elements in the copy array are filled with
 *   values initialized according to the given initializer.
 *
 */
inline fun <reified T> Array<T>.copyOf(newSize: Int, init: (Int)->T): Array<T> {
    return if(newSize <= size) {
        this.copyOfRange(0, newSize)
    } else {
        val begin = this.copyOf()
        val end = Array(newSize - size) { init(it + size) }
        arrayOf(*begin, *end)
    }
}

/**
 * Returns new array which is a copy of the original array, resized to the given [newSize].
 * The copy is either truncated or padded at the end with `null` values if necessary.
 *
 * - If [newSize] is less than the size of the original array, the copy array is truncated to the [newSize].
 * - If [newSize] is greater than the size of the original array, the extra elements in the copy array are filled with
 *   values initialized according to the given initializer.
 *   The initializer gives you access to the array being created, but accessing to value which
 *   are not yet defined will result in an IndexOutOfBoundsException
 *
 */
inline fun <reified T> Array<T>.copyOf(newSize: Int, init: (Int, Array<T>)->T): Array<T> {
    return if(newSize <= size) {
        this.copyOfRange(0, newSize)
    } else {
        val begin = this.copyOf()
        val result = arrayListOf(*begin)

        for(i in size until newSize) {
            result.add(init(i, result.toTypedArray()))
        }

        result.toTypedArray()
    }
}