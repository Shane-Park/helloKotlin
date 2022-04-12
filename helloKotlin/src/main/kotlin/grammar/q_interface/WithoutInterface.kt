package grammar.q_interface

/**
 * Kotlin does not support multiple inheritance
 */
fun main() {
    val obj1 = TestClass1()
    var obj2 = TestClass2()
    testFun1(obj1)
    testFun2(obj2)
}

open abstract class AbstractClass1() {
    open abstract fun abstractMethod1()
}

open abstract class AbstractClass2() {
    open abstract fun abstractMethod2()
}

fun testFun1(obj1: AbstractClass1) {
    obj1.abstractMethod1()
}

fun testFun2(obj2: AbstractClass2) {
    obj2.abstractMethod2()
}

class TestClass1 : AbstractClass1() {
    override fun abstractMethod1() {
        println("Abstract Method1 of TestClass1")
    }
}

class TestClass2 : AbstractClass2() {
    override fun abstractMethod2() {
        println("Abstract Method2 of TestClass2")
    }

}
