package org.apache.commonskt.io

actual fun eprint(string: String) {
    console.error(string)
}

actual fun eprintln(string: String) {
    console.error(string + "\n")
}