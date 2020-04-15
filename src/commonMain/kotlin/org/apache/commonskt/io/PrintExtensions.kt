package org.apache.commonskt.io

/**
 * Prints a message on the default error stream, followed by a line-return character
 * @param string the message to display
 */
expect fun eprintln(string: String)

/**
 * Formats a message on the default error stream
 * @param string the message format to display
 * @param args the values to inject in the message format
 */
expect fun eformat(string: String, vararg args: Any)