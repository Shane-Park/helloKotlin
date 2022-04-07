package grammar.h_oop

fun main() {
    val customClass = CustomClass()
    println()

    val customClass2 = CustomClass2()
    println()

    val customClass2_2 = CustomClass2(5,10)
    println("customClass2_2.property1 = ${customClass2_2.property1}")
    println("customClass2_2.property2 = ${customClass2_2.property2}")
    println()

    val customClass6 = CustomClass6(10, 20)
    println("customClass6.n1 = ${customClass6.n1}")
    println("customClass6.n2 = ${customClass6.n2}")

    println()
    val secondaryConstructor = CustomClass6(10)

}

class CustomClass {
    init{
        println("When the class is constructed, 'init' executed automatically")
    }
}

class CustomClass2 {
    var property1 = 0
    var property2 = 0

    constructor(){
        println("Constructor without any parameter")
    }
    constructor(num1:Int, num2:Int) {
        println("Constructor with two parameters")
        property1 = num1
        property2 = num2
    }
}

/**
 * java: public final class CustomClass3 {}
 */
class CustomClass3

/**
 * Default Constructor
 *java:
 * public final class CustomClass4 {
 *  public CustomClass4(int n1, int n2) {
 *  }
 * }
 */
class CustomClass4(n1:Int, n2:Int)

/** java:
 * public final class CustomClass5 {
 * private int n1;
 * private final int n2;
 *
 * public final int getN1() {return this.n1;}
 *
 * public final void setN1(int var1) {this.n1 = var1;}
 *
 * public final int getN2() {return this.n2;}
 *
 * public CustomClass5(int n1, int n2) {
 * this.n1 = n1;
 * this.n2 = n2;
 * }
 * }
 */
class CustomClass5 constructor(var n1:Int, val n2:Int)
// could redundant 'constructor' text
class CustomClass6 (var n1:Int, val n2:Int) {
    init{
        println("called init")
        println("Properties are already set. Constructor called before init.")
        println("n1 = ${n1}")
        println("n1 = ${n2}")
    }

    constructor(n1:Int) :this(n1,0) {
        println("if you want to call secondary constructor, you should call primary constructor.")
        println("Secondary constructor called at the last")
    }
}
