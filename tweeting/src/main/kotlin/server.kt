package kweb.template

import kotlinx.serialization.json.jsonPrimitive
import kweb.ButtonType
import kweb.InputType
import kweb.Kweb
import kweb.button
import kweb.form
import kweb.input
import kweb.new
import kweb.plugins.fomanticUI.FomanticUIPlugin

fun main() {
    Kweb(port = 16097, debug = true, plugins = listOf(FomanticUIPlugin())) {
        doc.body.new {
            form {
                val input = input(type = InputType.text)
                input.setValue("haha")

                val button = button(type = ButtonType.submit)
                button.text("Tweet")
                button.on(retrieveJs = input.valueJsExpression, preventDefault = true).click {event ->
                    println("I want to store the entry here... `${event.retrieved.jsonPrimitive.content}`")
                }
            }
        }
    }
}
