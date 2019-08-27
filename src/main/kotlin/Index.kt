package index

import app.app
import kotlinext.js.requireAll
import react.dom.render
import kotlin.browser.document

fun main() {
    requireAll(kotlinext.js.require.context("./../../src", true, js("/\\.(sa|sc|c)ss$/")))

    val env = js("process.env.NODE_ENV")
    console.log("env: $env")

    render(document.getElementById("root")) {
        app()
    }
}