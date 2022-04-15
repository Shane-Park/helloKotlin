package advanced.i_sealed_class

fun main() {
    checkNumber(Number.TWO)
    println()

    val one = SealedNumber.SealedOne(2, "world")
    val two = SealedNumber.SealedTwo(2, 2.0)
    checkNumber2(one)
    checkNumber2(two)
}

enum class Number(val num: Int) {
    ONE(1), TWO(2), THREE(3)
}

fun checkNumber(n: Number) {
    when (n) {
        Number.ONE -> println("it is one")
        Number.TWO -> println("it is two")
        Number.THREE -> println("it is three")
    }

    when (n.num) {
        1 -> println("it is 1")
        2 -> println("it is 2")
        3 -> println("it is 3")
    }
}

sealed class SealedNumber {
    class SealedOne(val n: Int, val n2:String): SealedNumber()
    class SealedTwo(val n: Int, val n2:Double): SealedNumber(){
        fun sealedFunction() {
            println("sealed function")
        }
    }
    class SealedThree(val n: Int): SealedNumber()
}

fun checkNumber2(n: SealedNumber) {
    when(n) {
        is SealedNumber.SealedOne -> {
            println("n = ${n.n}")
            println("It's one")
            println("n2 = ${n.n2}")
        }
        is SealedNumber.SealedTwo -> {
            println("n = ${n.n}")
            println("It's two")
            println("n2 = ${n.n2}")
            n.sealedFunction() // smart casting
        }
        is SealedNumber.SealedThree -> {
            println("n = ${n.n}")
            println("It's three")
        }
    }

}
