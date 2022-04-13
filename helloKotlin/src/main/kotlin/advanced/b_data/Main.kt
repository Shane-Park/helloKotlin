package advanced.b_data

/**
 * Data Class
 * - equals
 * - hashCode
 * - copy
 * - toString
 * - component{N}
 */
fun main() {
    var obj = TestClass(1, 2)
    var obj2 = DataClass(3, 4)

    println("obj.n1 = ${obj.n1}")
    println("obj.n2 = ${obj.n2}\n")

    println("obj2.n1 = ${obj2.n1}")
    println("obj2.n2 = ${obj2.n2}")

    var obj3 = TestClass(1, 2, 3)
    var obj4 = DataClass(1, 2, 3)

    obj3.testMethod()
    obj4.testMethod()
    println()

    var obj5 = TestClass(1, 2, 3)
    var obj6 = TestClass(1, 2, 3)
    if (obj5 == obj6) {
        println("obj5 == obj6")
    } else {
        println("obj5 != obj6")
    }
    println()

    val same = DataClass(1, 2, 3) == DataClass(1, 2, 3)
    if (same) {
        println("DataClass(1, 2, 3) == DataClass(1, 2, 3)")
    } else {
        println("DataClass(1, 2, 3) != DataClass(1, 2, 3)")
    }
    println()

    /**
     * only compare values from Primary Constructor variables
     */
    val same2 = DataClass(1, 2, 3) == DataClass(1, 2, 5)
    if (same2) {
        println("DataClass(1, 2, 3) == DataClass(1, 2, 5)")
    } else {
        println("DataClass(1, 2, 3) != DataClass(1, 2, 5)")
    }
    println()


    // Only DataClass Has Copy Method
//    obj3.copy()
    val copy = obj4.copy()
    println("obj3 = ${obj3}")
    println("obj4 = ${obj4}")
    println("copy==obj4 = ${copy == obj4}")
    println()

    /**
     * component1() return the first variable declared in primary Constructor
     */
    println("obj4.component1() = ${obj4.component1()}")
    println("obj4.component2() = ${obj4.component2()}\n")

    var dataClass2 = DataClass2(1, 2, 3)
    println("dataClass2.n1 = ${dataClass2.n1}")
    println("dataClass2.n2 = ${dataClass2.n2}")
    println("dataClass2.n3 = ${dataClass2.n3}\n")

    /**
     * Component is related to Object disassemble
     */
    val(num10, num11) = obj4
    println("num10 = ${num10}")
    println("num10 = ${num11}\n")

    val(n1,n2,n3) = dataClass2
    println("n1 = ${n1}")
    println("n2 = ${n2}")
    println("n3 = ${n3}")


}

class TestClass(var n1: Int, var n2: Int) {
    var n3: Int = 0

    init {
        println("init of TestClass")
    }

    constructor(a1: Int, a2: Int, a3: Int) : this(a1, a2) {
        this.n3 = a3
    }

    fun testMethod() {
        println("test method of TestClass")
    }
}

/**
 * can't make it abstract / open
 */
data class DataClass(var n1: Int, var n2: Int) {
    var n3: Int = 0

    init {
        println("init of DataClass")
    }

    constructor(a1: Int, a2: Int, a3: Int) : this(a1, a2) {
        this.n3 = a3
    }

    fun testMethod() {
        println("test method of DataClass")
    }
}

data class DataClass2(var n1: Int, var n2: Int, var n3: Int)
