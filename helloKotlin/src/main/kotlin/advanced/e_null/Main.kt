package advanced.e_null

fun main() {
    testFun1("hi")
    // NullPointerException
//    testFun1(null)
    println()

    testFun2(null)
    println()

    testFun3("Not null")
    testFun3(null)

}

fun testFun1(str: String?) {
    val value: String = str!!
    println("value = ${value}")
}

fun testFun2(str: String?) {
    val value: String = str ?: "defaultString"
    println("value = ${value}")
}

fun testFun3(str:String?){
    println("str : $str")
    // with `?` if it has null it just returns null
    println("str length : ${str?.length}")
}
