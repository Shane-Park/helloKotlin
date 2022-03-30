package grammar.e_function

fun main() {
    var a = 1
    var b = 2
    println("a / b = ${divide(a, b)}")
    println("b / a = ${divide(num2 = a, num1 = b)}")
}

fun divide(num1: Int, num2: Int): Double {
    return num1.toDouble() / num2
}
