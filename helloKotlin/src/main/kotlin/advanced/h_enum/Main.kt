package advanced.h_enum

import advanced.h_enum.Direction.*

fun main() {
    printDirection(SOUTH)
    printDirection(EAST)
    println()

    val dir: Direction = WEST
    printDirection(dir)
    println()

    printNumber(Number.TEN)

}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Number(val n: Int, val str: String) {
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    TEN(10, "ten")
}

fun printDirection(d: Direction) {
    when (d) {
        NORTH -> println("north")
        EAST -> println("east")
        WEST -> println("west")
        SOUTH -> println("south")
    }
}

fun printNumber(n:Number){
    println("${n.str} is ${n.n}")
}
