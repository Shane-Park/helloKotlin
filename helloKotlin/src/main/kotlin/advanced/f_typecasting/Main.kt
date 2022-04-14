package advanced.f_typecasting

fun main() {
    val obj1: SubClass1 = SubClass1()
    val obj2: SubClass2 = SubClass2()


    // parent class / interface Type can have subClass
    val super1: SuperClass1 = obj1
    val inter1: Inter1 = obj2

    // opposite is not able
//    val obj3:SubClass1 = Super1
//    val obj4:SubClass2 = inter1
    println()

    /**
     * as : force type casting
     */
    super1 as SubClass1
    inter1 as SubClass2

    // because of `as` it becomes possible
    super1.subMethod1()
    inter1.subMethod2()

    // Not possible because each of them are not related at all.
//    inter1 as SubClass1

    /**
     * is: if type casting is possible, it converts and return true
     * be used in if block
     */
    val obj3: SubClass1 = SubClass1()
    val chk1 = obj3 is SuperClass1
    println("chk1 = ${chk1}\n") // can convert

    // Because type doesn't match it throws error
//    val chk2 = obj3 is Inter1

    val super3: SuperClass1 = obj3
    val chk3 = super3 is SubClass1
    println("chk3 = ${chk3}")
    if (super3 is SubClass1) {
//        super3 as SubClass1
        // if `if-is` is true, it does smart casting
        super3.subMethod1()
    }
    // only possible inside the if block
//    super3.subMethod1()
    println()

    /**
     * Any + is
     */
    val obj10: SubClass1 = SubClass1()
    val obj11: SubClass2 = SubClass2()

    anyMethod(obj10)
    anyMethod(obj11)
    anyMethod(123)
    anyMethod("STRING")
    println()

    val number1: Int = 100
    val number2: Long = number1.toLong()
    println("number2 = ${number2}")

    val str1: String = "100"
    val number3: Int = str1.toInt()
    println("number3 = ${number3}\n")

    val str2: String = "Hello!"
    // NumberFormat Exception
//    val number4:Int = str2.toInt()
//    println("number4 = ${number4}")


}

open class SuperClass1

interface Inter1

class SubClass1 : SuperClass1() {
    fun subMethod1() {
        println("SubMethod1 of SubClass1")
    }
}

class SubClass2 : Inter1 {
    fun subMethod2() {
        println("SubMethod2 of SubClass2")
    }
}

fun anyMethod(obj: Any) {
    if (obj is SubClass1) {
        obj.subMethod1()
    } else if (obj is SubClass2) {
        obj.subMethod2()
    } else if (obj is Int) {
        println("Integer Type!")
    } else if (obj is String) {
        println("String Type!")
    }
}
