package kweb.template

import kweb.ButtonType
import kweb.Kweb
import kweb.a
import kweb.button
import kweb.div
import kweb.form
import kweb.h1
import kweb.input
import kweb.new
import kweb.p
import kweb.plugins.fomanticUI.FomanticUIPlugin
import kweb.plugins.fomanticUI.fomantic
import kweb.route
import kweb.state.KVar
import kweb.state.ObservableList
import kweb.state.renderEach

val list = ObservableList<String>()

fun main() {
    Kweb(port = 16097, plugins = listOf(FomanticUIPlugin())) {
        doc.body.new {
            route {
                div(fomantic.ui.menu) {
                    div(fomantic.header.item) {
                        a(href = "/").text("Routing demo")
                    }
                    a(fomantic.item, href = "/create").text("Create new entry")
                }

                path("/") {
                    println("Rendering root page")
                    h1().text("root page")
                    renderEach(list) {
                        p().text(it)
                    }
                }

                path("/create") {
                    h1().text("create page")

                    lateinit var inputVar: KVar<String>
                    form {
                        inputVar = input().value
                        button(type = ButtonType.submit).text("Submit")
                    }.on(preventDefault = true).submit {
                        list.add(inputVar.value)
                        url.value = "/"
                    }
                }
            }
        }
    }
}
