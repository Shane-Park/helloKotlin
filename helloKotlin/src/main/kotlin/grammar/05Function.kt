package grammar

//fun main() {
//    printHello()
//}

fun printHello() {
    println("Hello Kotlin")
}

//fun main() {
//    var a = 1
//    var b = 2
//    print("a + b = ${plus(a,b)}")
//}

fun plus(a: Int, b: Int): Int {
    return a + b;
}

//fun main() {
//    var a = 1
//    var b = 2
//    println("a / b = ${divide(a, b)}")
//    println("b / a = ${divide(num2 = a, num1 = b)}")
//}

fun divide(num1: Int, num2: Int): Double {
    return num1.toDouble() / num2
}

//fun main() {
//    println(sum(b = 2))
//}

fun sum(a: Int = 0, b: Int = 1, c: Int = 2): Int {
    return a + b + c
}

//fun main() {
//    printHelloWorld()
//}

fun printHelloWorld(): Unit {
    println("Hello World")
}

//fun main() {
//    overloading();
//    overloading(1);
//    overloading("Hello");
//}

fun overloading(s: String) {
    println("one String Parameter: $s")
}

fun overloading(i: Int) {
    println("one int Parameter: $i")
}

fun overloading() {
    println("No parameter")
}

fun main() {
    func1()
}

fun func1() {
    println("func1")
    fun func2(){
        println("func2")
        fun func3(){
            println("func3")
        }
        func3()
    }
    func2()
}
