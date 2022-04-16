package collection.e_set

fun main() {
    val set = setOf(3, 1, 2, 3, 1, 2, 3, 1)
    println("set = ${set}")

    val mutableSet = mutableSetOf<Int>()
    println("set2 = ${mutableSet}")

    for (i in set) {
        println("i = ${i}")
    }
    println("set.size = ${set.size}\n")

    mutableSet.addAll(set)
    mutableSet.add(5)
    mutableSet.add(6)
    println("mutableSet = ${mutableSet}")
    println("mutableSet.toList() = ${mutableSet.toList()}");

}
