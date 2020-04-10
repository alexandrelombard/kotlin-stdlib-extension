package org.apache.commonskt.io

actual fun eprintln(string: String) {
    console.error(string)
}

actual fun eformat(string: String, vararg args: Any) {
    var string = string
    val argsList = args.toMutableList()

    while(argsList.isNotEmpty()) {
        val first = argsList.first()
        string = string.replace("/%(?:\\d+\\\$)?[dfsu]/", first.toString())
    }

    console.error(string)
}