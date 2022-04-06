package grammar.g_control_flow_statements

fun main() {
    var i = 1
    while (i <= 10) {
        println("i++ = ${i++}")
    }

    println()

    do {
        println("i-- = ${i--}")
    } while (i >= 1)

}
