package org.apache.commonskt

actual fun assert(value: Boolean) {
    kotlin.assert(value)
}

actual inline fun assert(value: Boolean, lazyMessage: () -> Any) {
    kotlin.assert(value, lazyMessage)
}