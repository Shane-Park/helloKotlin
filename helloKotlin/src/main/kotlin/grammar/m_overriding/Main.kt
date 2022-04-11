package grammar.m_overriding

fun main() {
    val obj = SubClass()
    println("obj.subNum1 = ${obj.subNum1}")
    obj.subMethod()

    println("obj.superNum1 = ${obj.superNum1}")
    obj.superMethod()

    println()

    val obj2: SuperClass = obj

    println("obj2.superNum1 = ${obj2.superNum1}")
    obj2.superMethod()

//    can't use method from SubClass
//    obj2.subMethod()
    println()

    val obj3 = SubClass2();
    obj3.superMethod()

    val obj4: SuperClass2 = obj3
    // Even if obj4 is SuperClass type it calls overridden method.
    // Because on handling event
    obj4.superMethod()

    println()

    val obj5 = SuperClass3()
    overridingTest(obj5)
    val obj6 = SubClass3()
    overridingTest(obj6)

}

open class SuperClass {
    var superNum1 = 1

    fun superMethod() {
        println("Super Method from SuperClass")
    }

}

class SubClass : SuperClass(){
    var subNum1 = 2
    fun subMethod() {
        println("Sub Method from SubClass")
    }
}

open class SuperClass2 {
    open fun superMethod() {
        println("superMethod from SuperClass2")
    }
}

class SubClass2: SuperClass2() {
    override fun superMethod() {
        println("SuperMethod2 overridden in SubClass2")
    }
}

open class SuperClass3 {
    open fun superMethod3() {
        println("SuperMethod3 from SuperClass3")
    }
}

class SubClass3 : SuperClass3() {
    override fun superMethod3() {
        println("SubMethod3 from SubClass3")
    }
}

fun overridingTest(obj1:SuperClass3) {
    obj1.superMethod3()
}
