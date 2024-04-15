package dev.pschmalz.ecomparator.user_interactor

import dev.pschmalz.ecomparator.user_interactor.boundary.Repository
import dev.pschmalz.ecomparator.user_interactor.data.EntityType

class EntityTypeListing(private val repository: Repository) {
    fun getEntityTypes() =
        repository.getEntityTypeNames()
            .let {repository.getQuantityTypeNamesForEntityTypeNames(it)}
            .map {EntityType(it.key, it.value)}
}