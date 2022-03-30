package grammar.e_function

fun main() {
    overloading();
    overloading(1);
    overloading("Hello");
}

fun overloading(s: String) {
    println("one String Parameter: $s")
}

fun overloading(i: Int) {
    println("one int Parameter: $i")
}

fun overloading() {
    println("No parameter")
}

