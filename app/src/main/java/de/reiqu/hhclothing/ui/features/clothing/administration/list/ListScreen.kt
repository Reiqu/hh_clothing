package de.reiqu.hhclothing.ui.features.clothing.administration.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


class ListScreen (private val navController: NavController) {

    @Composable
    fun Content() {
        Surface() {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Tests")
                Text(text = "Test")
                Button(onClick = { navController.navigate("dashboard") }) {
                    Text("Zurück")
                }
            }
        }
    }
}