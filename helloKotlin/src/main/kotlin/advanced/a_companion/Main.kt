package advanced.a_companion

fun main() {
    val obj = KotlinClass()
    obj.testFun1()
    
    val obj2 = KotlinClass()
    println("obj2.a1 = ${obj2.a1}")
    obj2.testFun1()
    println()
    
    obj2.a1 = 200
    println("obj.a1 = ${obj.a1}")
    println("obj2.a1 = ${obj2.a1}")
    println()

    // In Kotlin can't acceess to static variable by referenced variables
    KotlinClass.companionFunction()
    println("TestClass.a2 = ${KotlinClass.a2}\n")
    
    val obj3 = JavaClass()
    println("obj3.javaNum = ${obj3.javaNum}")
    println("JavaClass.javaNum2 = ${JavaClass.javaNum2}")
    JavaClass.javaMethod2()

}

class KotlinClass {
    var a1: Int = 100
    fun testFun1() {
        println("test Fun1")
    }

    companion object{
        var a2 = 1000
        fun companionFunction(){
            println("companionFunction")
            // Can't acceess to a1 because it requires the object to be created.
//            println("a1 = ${a1}")
            println("a2 = ${a2}")
        }
        @JvmStatic var a3 = 300

        @JvmStatic fun jvmStaticMethod(){
            println("jvmStaticMethod called")
        }
    }
}
