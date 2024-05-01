package dev.pschmalz.ecomparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import dev.pschmalz.ecomparator.local_data.RepositoryImpl
import dev.pschmalz.ecomparator.ui.theme.EntityComparatorTheme
import dev.pschmalz.ecomparator.ui.theme.Typography
import dev.pschmalz.ecomparator.user_interactor.EntityTypeListing
import dev.pschmalz.ecomparator.user_interactor.data.EntityType
import dev.pschmalz.ecomparator.viewmodels.EntityTypeListViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entityTypeListViewModel = EntityTypeListViewModel()

        setContent {
            EntityComparatorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val entityTypes by entityTypeListViewModel.entityTypes.collectAsStateWithLifecycle()

                    EntityTypeList(entityTypes)
                }
            }
        }
    }
}

@Composable
fun EntityTypeList(entityTypes: List<EntityType>) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        entityTypes.map {
            EntityTypeItem(entityType = it, Modifier.padding(top = 5.dp))
        }
    }
}

@Composable
fun EntityTypeItem(entityType: EntityType, modifier: Modifier = Modifier) {
    Button(onClick = {}, modifier.fillMaxWidth(0.95f)) {
        Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
           Text(text = entityType.name, style = Typography.titleLarge)

            Divider(Modifier.padding(start = 10.dp).width(1.dp).height(20.dp))

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
