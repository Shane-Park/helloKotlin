package grammar.g_control_flow_statements

/**
 * if절은 java에서의 사용 방법 그대로.
 */
fun main() {

    var a1 = 5
//
//    if (a1 < 10) {
//        println("a1 < 10")
//    } else if (a1 > 10) {
//        print("a1 > 10")
//    }
//
//    if (a1 == 10) {
//        println("true")
//    } else {
//        println("false")
//    }
//    println()
//
//    // Ternary Operator in Kotlin.
//    val str: String = if (a1 == 5) "5" else "not 5"
//    println("str = $str")

    val str2:String = if(a1==5) {
        println("process the block then assign")
        "a1 is 5"
    }else {
        println("process the block before assign")
        "not 5"
    }
    println("str2 = $str2")


}
