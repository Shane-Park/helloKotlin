package grammar.o_this_super

fun main() {
    var obj = Class1()
    obj.method()
    println()

    var obj2 = SubClass()
    obj2.subMethod1()
}

class Class1 constructor(var n: Int) {
    var num = 100

    constructor() : this(100) {

    }

    fun method() {
        var num = 200
        println("num = ${num}")
        println("this.num = ${this.num}")

        fun method2() {
            println("inner method2")
        }

        method2()
        this.method2()

    }

    fun method2() {
        println("method2")
    }
}

open class SuperClass(var n: Int) {
    open var n1 = 100
    open fun superMethod() {
        println("Super method of Super Class")
    }
}

class SubClass : SuperClass(1) {
    override var n1 = 200
    fun subMethod1() {
        println("n1 : $n1")
        println("super.n1 = ${super.n1}")

        superMethod()
    }

    override fun superMethod() {
        super.superMethod()
        println("Super method of Sub Class")
    }
}

class SubClass2 : SuperClass {
    constructor(n: Int) : super(n) {

    }

    constructor(n: Int, n2: Int) : super(n2) {

    }
}
