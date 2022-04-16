package collection.a_array

fun main() {

    val array = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
    println("array.contentDeepToString() = ${array.contentDeepToString()}")

    for (ints in array) {
        for (int in ints) {
            println("int = ${int}")
        }
    }
    println()

    println("array[0].contentToString() = ${array[0].contentToString()}")
    println("array.get(0) = ${array.get(0).get(0)}")
    array.get(0).set(0, 0)
    println("array[0][0] = ${array[0][0]}\n")
    println("array.size = ${array.size}")

}

