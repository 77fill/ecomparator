package dev.pschmalz.ecomparator.user_interactor.boundary

interface Dao {
    fun getEntityTypeNames(): List<String>
    fun getQuantityTypeNamesForEntityTypeNames(entityTypes: List<String>): Map<String, List<String>>
}