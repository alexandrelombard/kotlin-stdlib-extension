package com.github.alexandrelombard.commonskt.io

actual fun eprint(string: String) {
    System.err.print(string)
}

actual fun eprintln(string: String) {
    System.err.println(string)
}