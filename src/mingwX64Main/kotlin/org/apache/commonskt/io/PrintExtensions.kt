package org.apache.commonskt.io

import platform.posix.fflush
import platform.posix.fprintf

private val STDERR_MINGWX64 = platform.posix.fdopen(2, "w")

actual fun eprintln(string: String) {
    fprintf(STDERR_MINGWX64, string + "\n")
    fflush(STDERR_MINGWX64)
}

actual fun eformat(string: String, vararg args: Any) {
    fprintf(STDERR_MINGWX64, string, args)
    fflush(STDERR_MINGWX64)
}