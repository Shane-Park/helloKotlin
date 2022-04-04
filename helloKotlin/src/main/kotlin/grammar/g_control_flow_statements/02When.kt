package grammar.g_control_flow_statements

import kotlin.random.Random

/**
 * switch in java
 */
fun main() {

    val v = Random.nextInt(0, 4)

    // General switch sentence

    when (v) {
        1 -> println("v == 1")
        2 -> println("v == 2")
        3 -> println("v == 3")
        else -> println("else v == $v")
    }

    // process 2 cases together

    when (v) {
        0, 1 -> println("0 or 1")
        2, 3 -> println("2 or 3")
        else -> println("else")
    }

    // float / double
    val d = 12.34

    when (d) {
        12.34 -> println("12.34")
        45.67 -> println("45.67")
        else -> println("else")
    }

    // String
    val names = arrayOf("철수", "영희", "민수")
    when (names[Random.nextInt(0, 3)]) {
        "철수", "영희" -> println("철수 or 영희")
        "민수" -> println("민수")
    }

    when (Random.nextInt(0, 5)) {
        in 0..2 -> println("0~2")
        in 3..4 -> println("3~4")
    }

    println("oddOrEven(1) = ${oddOrEven(1)}")
    println("oddOrEven(2) = ${oddOrEven(2)}")
    println("oddOrEven(0) = ${oddOrEven(0)}")

}


fun oddOrEven(num: Int) = when (num) {
    1 -> "홀수(1)"
    2 -> "짝수(2)"
    else -> "그 외"
}
