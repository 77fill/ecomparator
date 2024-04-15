package dev.pschmalz.ecomparator.user_interactor

import dev.pschmalz.ecomparator.user_interactor.boundary.Dao
import dev.pschmalz.ecomparator.user_interactor.data.EntityType

class EntityTypeListing(private val dao: Dao) {
    fun getEntityTypes() =
        dao.getEntityTypeNames()
            .let {dao.getQuantityTypeNamesForEntityTypeNames(it)}
            .map {EntityType(it.key, it.value)}
}