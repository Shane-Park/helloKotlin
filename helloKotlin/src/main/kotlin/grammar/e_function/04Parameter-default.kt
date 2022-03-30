package grammar.e_function

fun main() {
    println(sum(b = 2))
}

fun sum(a: Int = 0, b: Int = 1, c: Int = 2): Int {
    return a + b + c
}
