package dev.pschmalz.ecomparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Route.EntityTypesScreen.path) {
                composable(route = Route.EntityTypesScreen.path) { EntityTypesScreen(navController = navController) }
                composable(
                    route = Route.EntitiesScreen.path + "/{entityName}",
                    arguments = listOf(
                        navArgument(name = "entityName") {
                            type = NavType.StringType
                            nullable = true
                        }
                    )
                ) { EntitiesScreen(it.arguments?.getString("entityName")) }
            }
        }
    }
}

