package dev.pschmalz.ecomparator.local_data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT name FROM entity_type")
    fun getAllEntityTypeNames(): Flow<List<String>>

    @Query("SELECT entity_type_name, quantity_type_name " +
            "FROM entity_type_quantity_type " +
            "WHERE entity_type_name IN (:entityTypeNames)")
    fun getEntityTypeQuantityTypePairs_byEntityTypeNames(entityTypeNames: Flow<List<String>>): Flow<List<EntityTypeQuantityType>>
}