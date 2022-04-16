package collection.b_string

fun main() {
    // Kotlin manage String as array
    val str = "Hello world"
    println("str = ${str}")
    println("str[0] = ${str[0]}")
    println("str[0] = ${str[1]}\n")

//    can't set
//    str[0] = 'h'

    println("str.substring(1..3) = ${str.substring(1..6)}")
    println("\"hello world\".compareTo(\"HELLO WORLD\", true) = ${"hello world".compareTo("HELLO WORLD", true)}")
    println("str.split(\" \") = ${str.split(" ")}")


}
