package grammar.l_lazy_init

fun main() {
    val obj = LazyClass()
    println("obj.n1 = ${obj.n1}")
    println("obj.n2 = ${obj.n2}")

    obj.checkInitialization()
    println("obj.n3 = ${obj.n3}")
    println("obj.n4 = ${obj.n4}")
}

class LazyClass {
    var n1 = 1

    // Can not redundant Type as it can't guess the type
    var n2: Int

    lateinit var n3: String


    /**
     * for val variable we can do lazy init with lazy code block
     * This variable is initialized when use this variable
     */
    val n4:String by lazy {
        println("initializing n4")
        "Lazy String"
    }

    init {
        println("Lazy init n2")

        // Variables must be initialized to use
//        println("n2 = ${n2}")

        n2 = 2
    }

    fun checkInitialization() {
        if(!::n3.isInitialized) {
            println("Initializing n3")
            // if do not Initialize, it throws kotlin.UninitializedPropertyAccessException: lateinit property n3 has not been initialized
            n3 = "initialized"
        }
    }

}
