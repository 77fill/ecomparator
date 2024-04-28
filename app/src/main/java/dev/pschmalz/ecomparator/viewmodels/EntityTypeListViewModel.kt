package dev.pschmalz.ecomparator.viewmodels

import androidx.lifecycle.ViewModel
import dev.pschmalz.ecomparator.user_interactor.data.EntityType
import kotlinx.coroutines.flow.MutableStateFlow

class EntityTypeListViewModel : ViewModel() {
    val entityTypes = MutableStateFlow(listOf( EntityType(name = "Country", quantityTypes = listOf("Area"))))
}