package org.apache.commonskt

expect fun assert(value: Boolean)
expect fun assert(value: Boolean, lazyMessage: () -> Any)