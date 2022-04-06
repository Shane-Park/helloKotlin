package grammar.g_control_flow_statements

fun main() {

    val oneToTen = 1..10
    for (i in oneToTen) {
        println("i = $i")
    }

    println()

    val oneToTwentyStepTwo = 1..20 step 2
    for (j in oneToTwentyStepTwo) {
        println("j = ${j}")
    }

    println()

    val tenDownTo1 = 10 downTo 1 step 1
    for (k in tenDownTo1) {
        println("k = ${k}")
    }

    println()

    for (i in 1..10) {
        if (i % 2 == 1) continue
        if (i >= 7) break;
        println("i = ${i}")
    }

}
