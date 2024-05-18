package dev.pschmalz.ecomparator

enum class Route(val path: String) {
    EntityTypesScreen("/entity-types"),
    EntitiesScreen("/entities")
    ;

    fun withArguments(vararg args: List<String>) : String {
        return path + args.joinToString("/","/")
    }
}