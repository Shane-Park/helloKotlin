package advanced.j_reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

fun main() {

    // class Type
    val kotlinType: KClass<String> = String::class
    val javaType: Class<String> = String::class.java

    println("kotlinType = ${kotlinType}")
    println("javaType = ${javaType}\n")

    val str: String = "Hello"
    val kclass: KClass<out String> = str::class
    val jclass: Class<out String> = str::class.java
    println("kclass = ${kclass}")
    println("jclass = ${jclass}\n")

    val allKotlin: KClass<*> = str::class
    val allJava: Class<*> = str::class.java
    println("allKotlin = ${allKotlin}")
    println("allJava = ${allJava}\n")

    var test1: TestClass = TestClass()
    var testKotlinClass = test1::class
    var testJavaClass = test1::class.java
    println("testKotlinClass = ${testKotlinClass}")
    println("testJavaClass = ${testJavaClass}\n")

    println("test1::class.isAbstract : ${test1::class.isAbstract}")
    println("test1::class.isCompanion : ${test1::class.isCompanion}")
    println("test1::class.isData : ${test1::class.isData}")
    println("test1::class.isFinal : ${test1::class.isFinal}")
    println("test1::class.isOpen = ${test1::class.isOpen}")
    println("test1::class.isInner = ${test1::class.isInner}")
    println("test1::class.isSealed = ${test1::class.isSealed}\n")

    // constructor information
    val constructors = test1::class.constructors
    for (constructor in constructors) {
        println("constructor = ${constructor}")
        for (parameter in constructor.parameters) {
            println(" parameter.index = ${parameter.index}")
            println(" parameter.type = ${parameter.type}")
            println(" parameter.name = ${parameter.name}")
        }
    }
    println()

    // primary constructor
    val primaryConstructor = test1::class.primaryConstructor
    println("primaryConstructor = ${primaryConstructor}")
    if (primaryConstructor != null) {
        for (parameter in primaryConstructor.parameters) {
            println(" parameter = ${parameter}")
        }
    }

    // member properties
    for (property in test1::class.declaredMemberProperties) {
        println("property = ${property.returnType}")
        println("property = ${property.name}")
    }
    
    // methods
    for (declaredMemberFunction in test1::class.declaredMemberFunctions) {
        println("declaredMemberFunction.name = ${declaredMemberFunction.name}")
    }
}

class TestClass() {
    val n1 = 100
    val n2 = 200

    constructor(a1: Int) : this() {

    }

    constructor(a1: Int, a2: Int) : this() {

    }

    fun myMethod(a1:Int){

    }
}
