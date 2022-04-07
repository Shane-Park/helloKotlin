package grammar.h_oop

fun main() {

    val obj = MyClass()
    println("obj = $obj")

    val obj2: MyClass = MyClass()
    println("obj2 = ${obj2}")

    println("obj == obj2 = ${obj == obj2}")

    val obj3 = MyClass2()

    println()

    var obj4 = MyClass3()
    println("obj4.property1 = ${obj4.property1}")
    println("obj4.property2 = ${obj4.property2}")
    obj4.property2 = 2
    println("obj4.property2 = ${obj4.property2}")
    obj4.memberMethod1()
    obj4.memberMethod2()

}

class MyClass {}

// if there is no contents in the Class `{}` could be removed. it never happens in java but it does in Kotlin.
class MyClass2

class MyClass3 {
    val property1 = 0
    var property2 = 1
    // checking the java Decompiled code. We can see it automatically makes getter/setter

    // MemberMethod (Method)
    fun memberMethod1() {
        println("called Member Method1")
    }

    fun memberMethod2() {
        println("called Member Method2")
    }
}
