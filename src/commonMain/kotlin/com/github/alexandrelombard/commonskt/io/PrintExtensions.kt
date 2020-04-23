package com.github.alexandrelombard.commonskt.io

/**
 * Prints a message on the default error stream
 * @param string the message to display
 */
expect fun eprint(string: String)

/**
 * Prints a message on the default error stream, followed by a line-return character
 * @param string the message to display
 */
expect fun eprintln(string: String)