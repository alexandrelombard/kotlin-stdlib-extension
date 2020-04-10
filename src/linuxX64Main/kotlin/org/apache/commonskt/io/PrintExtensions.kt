package org.apache.commonskt.io

import platform.posix.fflush
import platform.posix.fprintf

private val STDERR_LINUXX64 = platform.posix.fdopen(2, "w")

actual fun eprintln(string: String) {
    fprintf(STDERR_LINUXX64, string + "\n")
    fflush(STDERR_LINUXX64)
}

actual fun eformat(string: String, vararg args: Any) {
    fprintf(STDERR_LINUXX64, string, args)
    fflush(STDERR_LINUXX64)
}