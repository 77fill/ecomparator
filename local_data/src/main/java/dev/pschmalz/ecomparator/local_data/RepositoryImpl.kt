package dev.pschmalz.ecomparator.local_data

import android.app.Application
import android.content.Context
import androidx.room.Room
import dev.pschmalz.ecomparator.user_interactor.boundary.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(context: Context) : Repository {
    val dao =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "ecomparator"
        )
            .build()
            .getDao()
    override fun getEntityTypeNames(): Flow<List<String>> {
        return dao.getAllEntityTypeNames()
    }

    override fun getQuantityTypeNamesForEntityTypeNames(entityTypes: Flow<List<String>>): Flow<Map<String, List<String>>> {
        return dao.getEntityTypeQuantityTypePairs_byEntityTypeNames(entityTypes)
                .map {
                    it.groupBy {
                        it.entityTypeName
                    }.entries.associate {
                        it.key to it.value.map { it.quantityTypeName }
                    }
                }
    }
}