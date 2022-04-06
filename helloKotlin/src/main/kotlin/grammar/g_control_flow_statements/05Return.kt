package grammar.g_control_flow_statements

fun main() {
    println("func1(10) = ${func1(10)}")
    println("sum(1,2) = ${sum(1, 2)}")
    println("div(1,2) = ${div(1, 2)}")
    println("div(1,2) = ${div(1, 0)}")
}

fun func1(n: Int): Int {
    println("func1 called")
    return n
}

fun sum(n1: Int, n2: Int): Int {
    return n1 + n2
}

fun div(n1: Int, n2: Int): Double {
    if (n2 == 0) {
        println("can't divide by 0")
        return -1.0
    }

    return n1 / n2.toDouble()
}
