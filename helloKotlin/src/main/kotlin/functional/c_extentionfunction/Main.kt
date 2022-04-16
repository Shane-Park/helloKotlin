package functional.c_extentionfunction

fun main() {
    val str = "string"
    println("str.getUpperString() = ${str.getUpperString()}")
}

fun String.getUpperString(): String {
    return this.uppercase()
}
