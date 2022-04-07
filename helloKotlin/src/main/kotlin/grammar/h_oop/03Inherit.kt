package grammar.h_oop

fun main() {

    val sub1 = SubClass()
    println("sub1.num1 = ${sub1.num1}")
    sub1.superMethod()
    sub1.subMethod()

}

/**
 * without open keyword, Kotlin class is always final which not allow Subclasses
 */
open class SuperClass(num1: Int) {
    var num1 = 1

    constructor() : this(1)

    fun superMethod() {
        println("Super Method called")
    }
}

/**
 *  You should call Super Construc
 */
class SubClass : SuperClass {
    constructor() : super(100)

    fun subMethod() {
        println("Sub method called")
    }
}

class SubClass2 : SuperClass()

class SubClass3 : SuperClass(100)

