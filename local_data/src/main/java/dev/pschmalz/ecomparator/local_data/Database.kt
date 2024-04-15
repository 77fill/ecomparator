package dev.pschmalz.ecomparator.local_data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(EntityType::class, QuantityType::class, EntityTypeQuantityType::class),
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getDao(): Dao
}