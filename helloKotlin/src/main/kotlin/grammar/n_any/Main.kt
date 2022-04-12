package grammar.n_any

/**
 * Any : Object class of Kotlin
 */
fun main() {
    val obj = CustomClass()
    println("obj = ${obj}\n")

    val obj2 = CustomClass2()

    testFunction(obj)
    testFunction(obj2)

}

class CustomClass{
    override fun toString(): String {
        return "overridden toString of CustomClass"
    }
}

class CustomClass2{
    override fun toString(): String {
        return "overridden toString of CustomClass2"
    }
}

/**
 * With Any type, any type could be a parameter
 */
fun testFunction(obj:Any) {
    println("obj = ${obj}")
}
