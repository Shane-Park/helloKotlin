package functional.e_higher_order_function

import kotlin.math.pow

fun main() {

    val fun1 = fun(n1: Int, n2: Int): Int {
        return n1 + n2
    }

    testFunc1(fun1, 100, 200)

    testFunc1(fun(n1: Int, n2: Int): Int {
        return n1 - n2
    }, 10, 20)

    val lambda1 = { x1: Int, x2: Int -> x1 * x2 }
    testFunc1(lambda1, 2, 5)
    testFunc1({ x1: Int, x2: Int -> x1.toDouble().pow(x2.toDouble()).toInt() }, 2, 5)
    println()

    val func2 = testFunc2()
    var result = func2(100, 200)
    println("result = ${result}")

    val func3 = testFunc3()
    val result3 = func3(100, 200)
    println("result3 = ${result3}\n")

    testFunc4({ n1: Int -> n1 + 10 }, 20)
    testFunc5(100, 200, { x1: Int, x2: Int -> x1 + x2 })
    testFunc5(100, 200) { x1: Int, x2: Int -> x1 + x2 }
    println()

    testFunc6({ x1: Int -> println(x1) })
    testFunc6 { x1: Int -> println(x1) }
    testFunc6 { println(it) }

}

fun testFunc1(method: (Int, Int) -> Int, n1: Int, n2: Int) {
    println("n1:$n1 ,n2:$n2 ,method(n1, n2) = ${method(n1, n2)}")
}

fun testFunc2(): (Int, Int) -> Int {
    return fun(x1: Int, x2: Int): Int {
        return x1 + x2
    }
}

fun testFunc3(): (Int, Int) -> Int {
    return { x1: Int, x2: Int -> x1 - x2 }
}

fun testFunc4(m1: (Int) -> Int, a1: Int) {
    var r4 = m1(a1)
    println("r1 = ${r4}")
}

fun testFunc5(a1: Int, a2: Int, m1: (Int, Int) -> Int) {
    val r5 = m1(a1, a2)
    println("r5 = ${r5}")
}

fun testFunc6(m1: (Int) -> Unit) {
    m1(100)
}
