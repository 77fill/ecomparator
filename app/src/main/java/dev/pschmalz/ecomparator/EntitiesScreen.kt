package dev.pschmalz.ecomparator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.pschmalz.ecomparator.ui.theme.EntityComparatorTheme
import dev.pschmalz.ecomparator.ui.theme.Typography
import dev.pschmalz.ecomparator.user_interactor.data.Entity
import dev.pschmalz.ecomparator.viewmodels.EntitiesScreenViewModel

@Composable
fun EntitiesScreen(entityName: String?) {
    val entitiesScreenViewModel = EntitiesScreenViewModel()

    EntityComparatorTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val entities by entitiesScreenViewModel.entities.collectAsStateWithLifecycle()

            EntityList(entities)
        }
    }
}

@Composable
fun EntityList(entities: List<Entity>, modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Entity Types", style = Typography.titleLarge)
        entities.map {
            EntityItem(entity = it)
        }
    }
}

@Composable
fun EntityItem(entity: Entity, modifier: Modifier = Modifier) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = entity.name)
    }
}

@Preview
@Composable
fun EntityListPreview() {
    EntityComparatorTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            EntityList(listOf(Entity("Germany")))
        }
    }
}
