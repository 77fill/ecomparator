package dev.pschmalz.ecomparator.local_data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quantity_type")
data class QuantityType(
    @PrimaryKey val name: String
)
