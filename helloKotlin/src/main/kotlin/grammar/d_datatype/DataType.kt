package grammar

fun main() {

    val i: Int = 123
    println("i: $i")

    var j = 456
    println("j: $j")

    j += 1
//    i += 1

    var nullable: Int? = null
    // can't assign null into not nullable Int
//    var notNull: Int = null
    var hundred: Int = 100
    var notNull: Int = 200

    println("nullable: $nullable")
    println("hundred: $hundred")
    println("notNull: $notNull")

    // NullPointException
//    notNull = nullable!!

}

