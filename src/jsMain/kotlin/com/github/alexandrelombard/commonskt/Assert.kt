package com.github.alexandrelombard.commonskt

actual fun assert(value: Boolean) {
    if(!value) throw AssertionError()
}

actual inline fun assert(value: Boolean, lazyMessage: () -> Any) {
    if(!value) throw AssertionError(lazyMessage.invoke())
}