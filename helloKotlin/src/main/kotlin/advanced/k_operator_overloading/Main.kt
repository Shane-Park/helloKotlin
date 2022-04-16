package advanced.k_operator_overloading

fun main() {
    val num1 = 100
    val num2 = 200
    println("num1+num2 = ${num1 + num2}")
    println("num1.plus(num2) = ${num1.plus(num2)}")

    val obj1 = TestClass(100, 200)
    val obj2 = TestClass(1000, 2000)
    println("obj1+obj2 = ${obj1 + obj2}")
    println("obj1-obj2 = ${obj1 - obj2}")
}

class TestClass(var a1: Int, var a2: Int) {
    operator fun plus(target: TestClass): TestClass {
        var r1 = this.a1 + target.a1
        var r2 = this.a2 + target.a2
        return TestClass(r1, r2)
    }

    operator fun minus(target: TestClass): TestClass {
        var r1 = this.a1 - target.a1
        var r2 = this.a2 - target.a2
        return TestClass(r1, r2)
    }

    override fun toString(): String {
        return "TestClass(a1=$a1, a2=$a2)"
    }

}
