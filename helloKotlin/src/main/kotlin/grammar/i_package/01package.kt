package grammar.i_package

import grammar.i_package.pkg.functionInPackage
import grammar.i_package.pkg2.*

fun main() {

    // Class in another package
    val classInAPackage = grammar.i_package.pkg.ClassInAPackage();

    // without import
    grammar.i_package.pkg.functionInPackage();

    // with import
    functionInPackage();

    ClassInPackage2().method()
    ClassInPackage2_2().method()

}
