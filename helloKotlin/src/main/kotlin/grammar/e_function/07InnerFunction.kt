package grammar.e_function

fun main() {
    func1()
}

fun func1() {
    println("func1")
    fun func2() {
        println("func2")
        fun func3() {
            println("func3")
        }
        func3()
    }
    func2()
}
