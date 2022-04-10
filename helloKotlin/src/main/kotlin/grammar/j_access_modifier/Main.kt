package grammar.j_access_modifier

import `package`.DifferentModulePublicClass
import grammar.j_access_modifier.`package`.DifferentPackageInternalClass
import grammar.j_access_modifier.`package`.DifferentPackagePublicClass

fun main() {
    val myClass = MyClass(1, 2)
    println("myClass.n1 = ${myClass.n1}")
    println("myClass.n2 = ${myClass.n2}")
    println()

    // private class is not accessible from outside
//    val private = PrivateClass()

    val public = PublicClass()
    val internal = InternalClass()

    // private is not accessible from outside
//    DifferentPackagePrivateClass()
    val differentPackagePublicClass = DifferentPackagePublicClass()
    val differentPackageInternalClass = DifferentPackageInternalClass()

    DifferentModulePublicClass()
    // Internal is only accessible inside the module
//    DifferentModuleInnerClass()

    // private is not accessible
//    println("public.private = ${public.private}")
    println("public.public = ${public.public}")
    // protected is only for inherited
//    println("public.protected = ${public.protected}")
    println("public.internal = ${public.internal}")
    println()

    SubClass().subMethodMethod()
    println()


//    println("differentPackagePublicClass.private = ${differentPackagePublicClass.private}")
    println("differentPackagePublicClass.public = ${differentPackagePublicClass.public}")
//    println("differentPackagePublicClass.protected = ${differentPackagePublicClass.protected}")
    println("differentPackagePublicClass.internal = ${differentPackagePublicClass.internal}")
    println()

    SubclassOfDifferentPackage().subMethodMethod()
    /**
     * IN KOTLIN PACKAGE DOES NOT MATTER AT ALL ABOUT ACCESS MODIFIER
     */

}

class MyClass(var n1: Int, val n2: Int)

class SubClass : PublicClass() {
    fun subMethodMethod() {
        // private is not accessible
//    println("private = ${private}")
        println("=====SubClass started subMethodMethod ===")
        println("public = ${public}")
        println("protected = ${protected}")
        println("internal = ${internal}")
        println("=====SubClass finished subMethodMethod ===")
    }
}

class SubclassOfDifferentPackage : DifferentPackagePublicClass() {
    fun subMethodMethod() {
        // private is not accessible
//    println("private = ${private}")
        println("=====DifferentPackageSubClass started subMethodMethod ===")
        println("public = ${public}")
        println("protected = ${protected}")
        println("internal = ${internal}")
        println("=====DifferentPackageSubClass finished subMethodMethod ===")
    }
}

class SubclassOfDifferentModule : DifferentModulePublicClass() {
    fun subMethodMethod() {
        // private is not accessible
//    println("private = ${private}")
        println("=====DifferentPackageSubClass started subMethodMethod ===")
        println("public = ${public}")
        println("protected = ${protected}")
        // internal member is not accessible since it's not in the same module
//        println("internal = ${internal}")
        println("=====DifferentPackageSubClass finished subMethodMethod ===")
    }
}
