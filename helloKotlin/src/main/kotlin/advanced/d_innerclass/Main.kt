package advanced.d_innerclass

fun main() {
    val outer = Outer()
    val inner = outer.Inner()
    inner.innerMethod()
    inner.innerMethod2()
    println()

    outer.outerMethod2()
    println()

    val extend = Extend()
    extend.abstractMethod()

    val impl = Impl()
    impl.interMethod()
    println()

    val anonymous = object : AbstractClass(){
        override fun abstractMethod() {
            println("abstractMethod from anonymous class")
        }
    }
    anonymous.abstractMethod()

    val anonymous2 = object:Interface{
        override fun interMethod() {
            println("interMethod from anonymous")
        }
    }
    anonymous2.interMethod()

}

class Outer {

    var outerMember = 1

    fun outerMethod() {
        println("Outer Method")
    }
    
    fun outerMethod2() {
        // can't use or call method or member from inner method
//        println("innerMember = ${innerMember}")
//        innerMethod2()
        val inner = Inner()
        println("inner.innerMember = ${inner.innerMember}")
        inner.innerMethod()
    }

    inner class Inner {
        val innerMember = 2

        fun innerMethod() {
            println("Inner Method")
        }

        fun innerMethod2() {
            println("InnerMethod2 Started")
            println("outerMember : $outerMember")
            outerMethod()
            println("InnerMethod2 Ended")
        }
    }
}

abstract class AbstractClass {
    abstract fun abstractMethod()
}

interface Interface {
    fun interMethod()
}

class Extend: AbstractClass(){
    override fun abstractMethod() {
        println("Abstract Method from Impl")
    }

}

class Impl: Interface {
    override fun interMethod() {
        println("interMethod from Impl")
    }
}
