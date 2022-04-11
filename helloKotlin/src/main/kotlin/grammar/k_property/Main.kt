package grammar.k_property

fun main() {
    val obj = MyClass1(1, 2)

    /**
     * if the property has getter and setter it has under_bar like n1
     * else if it only has getter it doesn't have under bar
     */
    println("obj.n1 = ${obj.n1}")
    println("obj.n1 = ${obj.n2}")

    obj.n1 = 3

    println("=================")

    val obj2 = MyClass2()
    println("obj2.n1 = ${obj2.n1}")
    println("obj2.n2 = ${obj2.n2}")

    obj2.n3 = 1
    println("obj2.n3 = ${obj2.n3}")

}

/**
 * var : Setter / Getter
 * val : Getter
 */
class MyClass1(var n1: Int, val n2: Int)

class MyClass2 {
    var n1: Int = 0
    val n2: Int = 0
    var n3: Int = 0
        get() {
            println("Getter is called")
            return field
        }
        set(value) {
            println("Setter is called")
            field = value
        }
}
