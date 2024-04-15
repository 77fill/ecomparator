package dev.pschmalz.ecomparator.local_data

import android.app.Application
import android.content.Context
import androidx.room.Room
import dev.pschmalz.ecomparator.user_interactor.boundary.Repository

class RepositoryImpl(context: Context) : Repository {
    val dao = Room.databaseBuilder(context, Database::class.java, "ecomparator")
    override fun getEntityTypeNames(): List<String> {

        TODO("Not yet implemented")
    }

    override fun getQuantityTypeNamesForEntityTypeNames(entityTypes: List<String>): Map<String, List<String>> {
        TODO("Not yet implemented")
    }
}