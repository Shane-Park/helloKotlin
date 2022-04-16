package functional.a_intro

fun main() {
    println("testFun(100,200) = ${testFun(100, 200)}")
    println("testFun2(100,200) = ${testFun2(100, 200)}")
    println("testFun3(100,200) = ${testFun3(100, 200)}")
    println()

    // Labmda

    val lambdaSum: (Int, Int) -> Int = { a1, a2 -> a1 + a2 }
    println("lambda(1,2) = ${lambdaSum(1, 2)}")

    val lambda2 = { a1: Int, a2: Int -> a1 + a2 }
    println("lamdba2(1,2) = ${lambda2(1, 2)}")

    val lambda3: (Int, Int) -> Int = { a1, a2 -> a1 + a2 }
    println("lambda3(5,6) = ${lambda3(5, 6)}")

    println("testFun4(100,200) = ${testFun4(100, 200)}")
    val lambda4 = { a1: Int, a2: Int ->
        val r1 = a1 + a2
        val r2 = a1 - a2
        r1 * r2
    }
    println("lambda4(100,200) = ${lambda4(100, 200)}\n")

    // anonymous function
    val anonymousFunction = fun(){
        println("it's anonymous function")
    }
    anonymousFunction();

}

fun testFun(a1: Int, a2: Int): Int {
    return a1 + a2
}

fun testFun2(a1: Int, a2: Int): Int = a1 + a2
fun testFun3(a1: Int, a2: Int) = a1 + a2

fun testFun4(a1: Int, a2: Int): Int {
    val r1 = a1 + a2
    val r2 = a1 - a2
    val r3 = r1 * r2
    return r3
}
