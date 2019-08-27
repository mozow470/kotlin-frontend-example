package app

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h2
import react.dom.p

class App : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        div {
            h2 { +"FrontendTest!" }
            p {+"フロントエンドのテストだよ！!"}
        }
    }
}

fun RBuilder.app() = child(App::class) {}