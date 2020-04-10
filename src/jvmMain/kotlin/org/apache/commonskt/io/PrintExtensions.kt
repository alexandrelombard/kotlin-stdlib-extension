package org.apache.commonskt.io

actual fun eprintln(string: String) {
    System.err.println(string)
}

actual fun eformat(string: String, vararg args: Any) {
    System.err.format(string, args)
}