package collection.a_array

fun main() {
    val array = arrayOf(1, 2, 3, 4, 5)
    println("array.contentToString = ${array.contentToString()}")

    val array2 = arrayOf(1, 2, 3.4, "string", false)
    println("array2.contentToString() = ${array2.contentToString()}\n")

    val array3 = intArrayOf(1, 2, 3, 4, 5)
    val array4 = doubleArrayOf(1.2, 3.0, 4.0001)
    val array5 = arrayOf<String>("hello", "world")
    println("array3.contentToString() = ${array3.contentToString()}")
    println("array4.contentToString() = ${array4.contentToString()}")
    println("array5.contentToString() = ${array5.contentToString()}\n")

    val array6 = Array(5, {0 })
    println("array6.contentToString() = ${array6.contentToString()}")

    println("Array(5, {it*2}) = ${Array(5) { it * 2 }.contentToString()}\n")

    // iterating
    for (item in array) {
        println("item = ${item}")
    }
    println()

    /**
     * ### methods
     * - plus
     * - sliceArray
     * - first
     * - last
     * - indexOf
     * - average
     */
    println("array.contentToString() = ${array.contentToString()}")
    val plus = array.plus(30);
    println("plus.contentToString() = ${plus.contentToString()}")

    var slice = array.sliceArray(1..3)
    println("spice = ${slice.contentToString()}")

    println("array.first() = ${array.first()}")
    println("array.last() = ${array.last()}")
    println("array.indexOf(5) = ${array.indexOf(5)}")
    println("array.average() = ${array.average()}")
    println("array.sum() = ${array.sum()}")
    println("array.size = ${array.size}")
    println("array.contains(10) = ${array.contains(10)}")
    println("10 in array = ${10 in array}")
    println("array.maxOrNull() = ${array.maxOrNull()}")
    println("array.minOrNull() = ${array.minOrNull()}")

    val beforeSort = arrayOf(1, 5, 2, 3, 4, 1, 25)
    val sorted = beforeSort.sortedArray()
    println("beforeSort = ${beforeSort.contentToString()}")
    println("sorted.contentToString() = ${sorted.contentToString()}")
    println("beforeSort.sortedArrayDescending().contentToString() = ${beforeSort.sortedArrayDescending().contentToString()}")
    

}
