package functional.b_inline

fun main() {
    testFunc1()
    testFunc1()
    println()

    testFun2()
    testFun2()
}

fun testFunc1(){
    println("testFunc1")
}

/**
 * not call the function but copy the code
 */
inline fun testFun2(){
    println("testFunc2")
}
