package app

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div
import react.dom.h2
import react.dom.p

interface AppState: RState {
    var counter: Int
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        counter = 0
    }

    private fun increaseCounter() {
        setState { counter += 1 }
    }

    override fun RBuilder.render() {
        div {
            h2 {+"Hello World Kotlin2Js x Gradle x React"}
            p { +"カウント数: ${state.counter}" }
            button {
                attrs.onClickFunction = { increaseCounter() }
                +"クリックして！"
            }
        }
    }
}

fun RBuilder.app() = child(App::class) {}