package dev.pschmalz.ecomparator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.pschmalz.ecomparator.ui.theme.EntityComparatorTheme
import dev.pschmalz.ecomparator.ui.theme.Typography
import dev.pschmalz.ecomparator.user_interactor.data.EntityType
import dev.pschmalz.ecomparator.viewmodels.EntityTypeListViewModel

@Composable
fun EntityTypesScreen(navController: NavController) {
    val entityTypeListViewModel = EntityTypeListViewModel()

    EntityComparatorTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val entityTypes by entityTypeListViewModel.entityTypes.collectAsStateWithLifecycle()

            EntityTypeList(entityTypes, navController)
        }
    }
}

@Composable
fun EntityTypeList(entityTypes: List<EntityType>, navController: NavController? = null) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Entity Types", style = Typography.titleLarge)
        entityTypes.map {
            EntityTypeItem(entityType = it, Modifier.padding(top = 5.dp), navController = navController)
        }
    }
}

@Composable
fun EntityTypeItem(entityType: EntityType, modifier: Modifier = Modifier, navController: NavController?) {
    Button(onClick = {navController?.navigate(Route.EntitiesScreen.path)}, modifier.fillMaxWidth(0.95f)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(text = entityType.name, style = Typography.titleMedium)

            Divider(
                Modifier
                    .padding(start = 10.dp)
                    .width(1.dp)
                    .height(20.dp))

            Text(
                text = entityType.quantityTypes.joinToString(", "),
                style = Typography.bodyMedium,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 10.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun EntityTypeListPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        EntityTypeList(listOf(
            EntityType(name = "Country", quantityTypes = listOf("Area")),
            EntityType(name = "Device", quantityTypes = listOf("Power Consumption", "Cost", "ASDf sdf dasf"))
        ))
    }
}
