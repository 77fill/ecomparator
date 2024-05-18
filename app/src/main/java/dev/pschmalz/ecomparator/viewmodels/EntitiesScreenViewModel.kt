package dev.pschmalz.ecomparator.viewmodels

import androidx.lifecycle.ViewModel
import dev.pschmalz.ecomparator.user_interactor.data.Entity
import kotlinx.coroutines.flow.MutableStateFlow

class EntitiesScreenViewModel : ViewModel() {
    val entities = MutableStateFlow(listOf(Entity("Germany")))
}