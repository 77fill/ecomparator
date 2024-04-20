package dev.pschmalz.ecomparator.user_interactor

import dev.pschmalz.ecomparator.user_interactor.boundary.Repository
import dev.pschmalz.ecomparator.user_interactor.data.EntityType
import kotlinx.coroutines.flow.map

class EntityTypeListing(private val repository: Repository) {
    fun getEntityTypes() =
        repository.getEntityTypeNames()
            .let {repository.getQuantityTypeNamesForEntityTypeNames(it)}
            .map { it.map { EntityType(it.key, it.value) } }
}