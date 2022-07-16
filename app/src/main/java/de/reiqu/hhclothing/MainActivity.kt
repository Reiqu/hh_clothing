package de.reiqu.hhclothing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.reiqu.hhclothing.ui.features.clothing.DetailScreen.DetailScreen
import de.reiqu.hhclothing.ui.features.clothing.administration.add.AddScreen
import de.reiqu.hhclothing.ui.features.clothing.administration.list.ListScreen
import de.reiqu.hhclothing.ui.features.dashboard.DashboardView
import de.reiqu.hhclothing.ui.theme.HHClothingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HHClothingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "dashboard") {
                        composable("dashboard") { DashboardView(navController).Dashboard() }
                        composable("clothing_list") { ListScreen(navController).Content() }
                        composable("clothing_add") { AddScreen(navController).Content() }
                        composable("clothing_details") { DetailScreen(navController).DetailScreenView() }
                    }
                }
            }
        }
    }
}