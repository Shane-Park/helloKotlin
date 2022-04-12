package grammar.q_interface

fun main() {
    val obj = ImplClass()
    testFun3(obj)
    testFun4(obj)
}

interface Interface1{
    fun inter1Method1() {
        println("inter1Method1 from Interface1")
    }
    fun inter1Method2()
}

interface Interface2 {
    fun inter2Method1() {
        println("inter2Method1 from Interface2")
    }
    fun inter2Method2()
}

fun testFun3(obj1:Interface1) {
    obj1.inter1Method1()
    obj1.inter1Method2()
}

fun testFun4(obj1:Interface2) {
    obj1.inter2Method1()
    obj1.inter2Method2()
}

class ImplClass : Interface1,Interface2 {
    override fun inter1Method2() {
        println("impl inter1Method2")
    }

    override fun inter2Method2() {
        println("impl inter2Method2")
    }

}
