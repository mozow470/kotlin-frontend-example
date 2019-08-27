package index

import kotlinext.js.requireAll

fun main(args: ArrayList<String>) {
    requireAll(kotlinext.js.require.context("", true, js("/\\.css$/")))

    val env = js("process.env.NODE_ENV")
    console.log("env: $env")
}