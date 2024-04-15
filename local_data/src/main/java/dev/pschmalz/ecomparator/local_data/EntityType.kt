package dev.pschmalz.ecomparator.local_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entity_type")
data class EntityType(
    @PrimaryKey val name: String
)