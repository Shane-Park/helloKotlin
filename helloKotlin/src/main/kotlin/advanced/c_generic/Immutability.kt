package advanced.c_generic

fun main() {

    /**
     * Immutability
     */
    val t: TestClass<SubClass> = TestClass<SubClass>()
    // if type is different, it does not matter whether it is super class or sub class. it can't use that Generic
//    val t2:TestClass<Subclass2> = TestClass<SubClass>()
//    val t3:TestClass<SuperClass> = TestClass<SubClass>()

    /**
     * Covariance
     */
    val t4: TestClass2<SubClass> = TestClass2<SubClass>()
    // can't use subClass as Generic but with `out` prefix in front of the Generic, could use Super type
//    val t5:TestClass2<Subclass2> = TestClass2<SubClass>()
    val t6: TestClass2<SuperClass> = TestClass2<SubClass>()

    /**
     * Contravariance
     */
    val t7: TestClass3<SubClass> = TestClass3<SubClass>()
    val t8: TestClass3<Subclass2> = TestClass3<SubClass>()
    // can't use SuperClass
//    val t9:TestClass<SuperClass> = TestClass3<SubClass>()


}

open class SuperClass

open class SubClass : SuperClass()

class Subclass2 : SubClass()

/**
 * Immutability
 */
class TestClass<T>()


/**
 * Covariance
 */
class TestClass2<out T>()

/**
 * Contravariance
 */
class TestClass3<in T>()
