package advanced.g_nullsafe_casting

fun main() {
    testMethod1("hi")
    testMethod1(null)
    println()

    testMethod2("Hello")
    testMethod2(null)
}

fun testMethod1(str:String?){
    println(str?.length)

    if(str is String) {
        // Smart casting
        println(str.length)
    }

    if(str != null){
        println(str.length)
    }

    // cant use nullable out of if {}
    // println(str.length)
}

fun testMethod2(str:Any?){
    if(str is String){
        println(str.length)
    }
    if(str != null) {
        // because type is Any, can't use .length
//        println(str.length)
    }
}
