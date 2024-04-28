package dev.pschmalz.ecomparator.local_data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "entity_type_quantity_type",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = EntityType::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("entity_type_name")),
        ForeignKey(
            entity = QuantityType::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("quantity_type_name"))
    ),
    primaryKeys = arrayOf(
        "entity_type_name",
        "quantity_type_name"
    )
)
data class EntityTypeQuantityType(
    @ColumnInfo(name = "entity_type_name")
    val entityTypeName: String,
    @ColumnInfo(name = "quantity_type_name")
    val quantityTypeName: String
)
