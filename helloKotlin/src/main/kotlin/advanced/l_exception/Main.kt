package advanced.l_exception

fun main() {
    try {
//        val n = 0 / 0

        val str: String? = null
        str!!.length
    } catch (e: java.lang.ArithmeticException) {
        System.err.println("ArithmeticException")
    } catch (e: java.lang.NullPointerException) {
        System.err.println("NPE")
    }
    println("reach this part")

}
