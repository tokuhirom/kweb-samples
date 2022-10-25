package kweb.template

import kotlinx.serialization.json.jsonPrimitive
import kweb.*
import kweb.plugins.fomanticUI.FomanticUIPlugin
import kweb.state.ObservableList
import kweb.state.renderEach

val list = ObservableList<String>()

fun main() {
    Kweb(port = 16097, debug = true, plugins = listOf(FomanticUIPlugin())) {
        doc.body.new {
            form {
                val input = input(type = InputType.text)
                input.setValue("haha")

                val button = button(type = ButtonType.submit)
                button.text("Tweet")
                button.on(retrieveJs = input.valueJsExpression, preventDefault = true).click {event ->
                    list.add(0, event.retrieved.jsonPrimitive.content)
                }
            }

            ul {
                renderEach(list) {msg ->
                    li().text(msg)
                }
            }
        }
    }
}
