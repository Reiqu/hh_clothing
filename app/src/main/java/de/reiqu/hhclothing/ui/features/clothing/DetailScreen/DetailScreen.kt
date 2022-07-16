package de.reiqu.hhclothing.ui.features.clothing.DetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

class DetailScreen(private val navController: NavController) {

    @Composable
    fun DetailScreenView() {
        
        Surface() {
            Column {
                Button(onClick = { navController.navigate("dashboard") }) {
                    Text("Zurück")
                }

                Divider()

                Text(text = "Titel")
                Text(text = "Beschreibung")
                Text(text = "Derzeitig in Hamburg:")
                Text(text = "0")
            }
        }
    }
}