package grammar.f_operator

fun main() {

    val a = 123
    val b = 4

    println("기본적인 사칙 연산은 java와 동일")
    println("a + b = ${a+b}")
    println("a - b = ${a-b}")
    println("a * b = ${a*b}")
    println("a / b = ${a/b}")
    println("a % b = ${a%b}")

    val range:IntRange = 10..20
    println("\nIntRange")
    println("10..20: $range")
    println("range.first = ${range.first}")
    println("range.last = ${range.last}")

    println("\na:$a, b:$b 일때 비교 연산")
    println("a==b = ${a==b}")
    println("a!=b = ${a!=b}")
    println("a>=b = ${a>=b}")
    println("a<=b = ${a<=b}")

}
