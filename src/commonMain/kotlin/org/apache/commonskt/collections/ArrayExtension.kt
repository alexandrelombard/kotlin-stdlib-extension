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
    return if(newSize <= this.size) {
        this.copyOfRange(0, newSize)
    } else {
        val begin = this.copyOf()
        val end = Array(newSize - this.size) { init(it + this.size) }
        arrayOf(*begin, *end)
    }
}