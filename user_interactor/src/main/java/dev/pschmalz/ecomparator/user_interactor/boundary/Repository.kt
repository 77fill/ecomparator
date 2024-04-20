package dev.pschmalz.ecomparator.user_interactor.boundary

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getEntityTypeNames(): Flow<List<String>>
    fun getQuantityTypeNamesForEntityTypeNames(entityTypes: Flow<List<String>>): Flow<Map<String, List<String>>>
}