package functional.d_infix

fun main() {
    println("50 add 30 = ${50 add 30}")
    println("50 mod 3 = ${50 mod 3}")
}

infix fun Int.add(n: Int): Int {
    return this + n
}

infix fun Int.mod(n: Int): Int {
    return this % n
}
