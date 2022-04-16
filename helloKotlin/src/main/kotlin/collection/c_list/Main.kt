package collection.c_list

fun main() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val list2 = listOf("text1", "text2", "text3")
    println("list1 = ${list1}")
    println("list2 = ${list2}\n")

    val list3 = mutableListOf<Int>()
    val list4 = mutableListOf("text1", "text2")

    // can't add to immutable list
//    list1.add()
    list3.add(1)

    val list5 = emptyList<Int>()
    val list6 = listOfNotNull(10, 20, null, 30, null, 40, null, 50)
    println("list6 = ${list6}")

    for (item in list1) {
        println("item = ${item}")
    }
    println("list1.size = ${list1.size}\n")

    val list7 = listOf(10, 10, 20, 20, 30, 30)
    println("list7.get(3) = ${list7.get(3)}")
    println("list7.indexOf(10) = ${list7.indexOf(10)}")
    println("list7.lastIndexOf(10) = ${list7.lastIndexOf(10)}\n")

    println("list1.subList(1,3) = ${list1.subList(1, 3)}")
    println("list3 = ${list3}")
    println("list3.add(2)")
    list3.add(2)
    println("list3.add(3)")
    list3.add(3)
    println("list3.addAll(listOf(4, 5, 6))")
    list3.addAll(listOf(4, 5, 6))
    println("list3 = ${list3}")
    println("list3.add(1, 100)")
    list3.add(1, 100)
    println("list3 = ${list3}")

    println("list3.remove(100)")
    list3.remove(100)
    println("list3 = ${list3}")
    println("list3.removeAll(listOf(4, 5, 6))")
    list3.removeAll(listOf(4, 5, 6))
    println("list3 = ${list3}")
    println("list3.removeAt(0)")
    list3.removeAt(0)
    println("list3[0] = 4")
    list3[0] = 4
    println("list3 = ${list3}")

    val mutable = list1.toMutableList();
    mutable.add(0)
    
}
