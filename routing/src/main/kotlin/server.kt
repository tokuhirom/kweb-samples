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
import kotlin.math.max
import kotlin.math.min

val list = mutableListOf<String>()

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
                    println("Rendering /")
                    url.value = "/entries/1"
                }

                path("/entries/{page}") { params ->
                    println("Rendering /entries/{page}")
                    val page = params["page"]!!.value.toInt()
                    println("page=$page")
                    val items = list.paginate(page = page, limit = 5)
                    items.forEach { item ->
                        p().text(item)
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

fun <E> List<E>.paginate(page: Int, limit: Int): List<E> {
    // from(inclusive)
    val from = if (this.isEmpty()) {
        0
    } else {
        min(limit * (page - 1), this.size)
    }

    // to(exclusive)
    val to = max(min(limit * page, this.size), 0)

    return this.subList(from, to)
}
