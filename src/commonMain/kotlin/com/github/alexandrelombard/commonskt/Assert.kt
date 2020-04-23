package com.github.alexandrelombard.commonskt

expect fun assert(value: Boolean)
expect fun assert(value: Boolean, lazyMessage: () -> Any)