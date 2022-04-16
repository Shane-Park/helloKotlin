package collection.b_string

fun main() {
    val str = "Hello"
    val str2 = str + " world"
    println("str = ${str}")
    println("str2 = ${str2}\n")

    val sb = StringBuffer("hello")
    val sb2 = sb.append(" World")
    println("sb = ${sb}")
    println("sb2 = ${sb2}")
    sb.insert(5, "insert")
    println("sb = ${sb}")
    sb.delete(5, 11);
    println("sb = ${sb}")

}
