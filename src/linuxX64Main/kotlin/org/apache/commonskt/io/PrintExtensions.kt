package org.apache.commonskt.io

import platform.posix.fflush
import platform.posix.fprintf

private val STDERR_LINUXX64 = platform.posix.fdopen(2, "w")

actual fun eprint(string: String) {
    fprintf(STDERR_LINUXX64, string)
    fflush(STDERR_LINUXX64)
}

actual fun eprintln(string: String) {
    fprintf(STDERR_LINUXX64, string + "\n")
    fflush(STDERR_LINUXX64)
}