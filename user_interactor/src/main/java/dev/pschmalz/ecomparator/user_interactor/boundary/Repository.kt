package dev.pschmalz.ecomparator.user_interactor.boundary

interface Repository {
    fun getEntityTypeNames(): List<String>
    fun getQuantityTypeNamesForEntityTypeNames(entityTypes: List<String>): Map<String, List<String>>
}