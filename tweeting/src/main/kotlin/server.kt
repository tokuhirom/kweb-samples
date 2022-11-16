package kweb.template

import kweb.ButtonType
import kweb.InputElement
import kweb.InputType
import kweb.Kweb
import kweb.button
import kweb.form
import kweb.input
import kweb.li
import kweb.new
import kweb.state.KVar
import kweb.state.ObservableList
import kweb.state.renderEach
import kweb.ul

val list = ObservableList<String>()

fun main() {
    Kweb(port = 16097) {
        doc.body.new {
            lateinit var inputElement: InputElement
            lateinit var input: KVar<String>

            form {
                inputElement = input(type = InputType.text)
                input = inputElement.value

                button(type = ButtonType.submit)
                    .text("Tweet")
            }.on(preventDefault = true).submit {
                list.add(0, input.value)
                inputElement.setValue("")
            }

            ul {
                renderEach(list) { msg ->
                    li().text(msg)
                }
            }
        }
    }
}
