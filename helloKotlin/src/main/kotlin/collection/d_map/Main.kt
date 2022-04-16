package collection.d_map

fun main() {
    val map = mapOf<String, Int>("ten" to 10, "five" to 5)
    println("map.get(\"five\") = ${map.get("five")}")
    println("map[\"five\"] = ${map["five"]}")

    val map2 = mutableMapOf<String, Int>()
    println("map2 = ${map2}\n")

    val map3 = mapOf<String, Any>("key1" to 10, "key2" to "hello")
    println("map3 = ${map3}")
    println("map3[\"key1\"] = ${map3["key1"]}\n")

    println("map.size = ${map.size}")
    println("map.keys = ${map.keys}")
    println("map.values = ${map.values}")
    println("map.containsKey(\"five\") = ${map.containsKey("five")}")
    println("map.containsValue(4) = ${map.containsValue(4)}\n")

    map2.put("key1", 1)
    map2["key2"] = 2
    println("map2 = ${map2}")
    println("map2.remove(\"key1\")")
    map2.remove("key1")
    println("map2 = ${map2}\n")

    val mutableMap = map.toMutableMap()
    mutableMap["three"] = 3
    println("mutableMap = ${mutableMap}")
    val immutable = mutableMap.toMap()
//    immutable.put();


}
