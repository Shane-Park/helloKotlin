package advanced.c_generic

fun main() {
    val class1 = Class1<Int>()
    class1.method1(1)

    Class1<String>().method1("Hello World")
    println()

    val class3 = Class2<Int>(5)
    class3.method(10)
    println()

    val class4 = Class2("hello")
    class4.method("World!")
    println()

    val class5 = Class3<Int, Char>()
    class5.method(5, 'a')
    println()

    val class6 = Class4<String, Double, Int, Class1<Int>>("hi", 1.0)
    class6.method(10, class1)


}

class Class1<T> {
    fun method1(n1: T) {
        println("n1 = ${n1}")
    }
}

class Class2<T>(var n1: T) {
    fun method(n2: T) {
        println("n1 = ${n1}")
        println("n2 = ${n2}")
    }
}

class Class3<T, T2> {
    fun method(n1: T, n2: T2) {
        println("n1 = ${n1}")
        println("n2 = ${n2}")
    }
}

class Class4<A, B, C, D>(var n1: A, var n2: B) {
    fun method(n3: C, n4: D) {
        println("n1 = ${n1}")
        println("n2 = ${n2}")
        println("n3 = ${n3}")
        println("n4 = ${n4}")
    }
}
