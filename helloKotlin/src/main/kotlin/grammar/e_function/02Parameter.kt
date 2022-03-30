package grammar.e_function

fun main() {
    var a = 1
    var b = 2
    print("a + b = ${plus(a,b)}")
}

fun plus(a: Int, b: Int): Int {
    return a + b;
}
