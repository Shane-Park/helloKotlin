package grammar.p_abstract_class

fun main() {
//    val obj = Super1()
//    testFun1(obj)
//    println()

    val obj2 = Sub1()
    testFun1(obj2)
    println()

    val obj3 = Sub2()
    testFun1(obj3)

}

open abstract class Super1 {
    fun method1() {
        println("Method1 of Super1")
    }

    open fun method2() {
        println("Method2 of Super1")
    }

    open abstract fun method3()
}

class Sub1 : Super1() {
    override fun method2() {
        println("Method2 of Sub1")
    }

    override fun method3() {
        TODO("Not yet implemented")
    }
}

class Sub2 : Super1() {
    override fun method3() {
        TODO("Not yet implemented")
    }
}

fun testFun1(obj1: Super1) {
    obj1.method1()
    obj1.method2()
}
