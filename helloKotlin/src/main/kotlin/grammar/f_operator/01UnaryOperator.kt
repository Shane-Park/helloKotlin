package grammar.f_operator

/**
 * 연산자: 특수문자를 연산자로 활용
 * 값을 정해져있는 규칙대로 계산하고 그 값을 반환
 * 코틀린은 연산자와 함수 두가지를 제공
 */

fun main() {
    var a = 1;
    var b = -1;

    println("+ 연산자는 아무런 변화를 주지 않는다.")
    var a1 = +a;
    var b1 = +b;
    println("a: $a, +a: $a1")
    println("b: $b, -b: $b1")

    println("\n- 연산자는 부호를 반대로 바꾼다.")
    var minusA = -a;
    var minusB = -b;
    println("a: $a, -a: $minusA")
    println("b: $b, -b: $minusB")
    println(".unanryMinus() 메서드와 동일한 기능을 한다.")
    println("a.unaryMinus() = ${a.unaryMinus()}")
    println("b.unaryMinus() = ${b.unaryMinus()}")

    var trueValue = true;
    var falseValue = false;
    println("\n! 연산자는 불리언 값을 반대로 바꾼다.")
    println("!trueValue: ${!trueValue}")
    println("!falseValue: ${!falseValue}")
    println(".not() 메서드와 동일한 기능을 한다.")
    println("trueValue.not() = ${trueValue.not()}")
    println("falseValue.not() = ${falseValue.not()}")

    println("\n증감 연산자는 java에서와 활용 방법이 동일하다. .inc() 메서드를 호출한다.")
    println("a: $a")
    println("a++: ${a++}")
    println("a: $a")
    println("++a: ${++a}")

}

