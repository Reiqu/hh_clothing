package de.reiqu.hhclothing.ui.features.clothing.administration.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class AddScreen (private val navController: NavController) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content() {
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    modifier = Modifier.padding(8.dp)
                        ) {
                    Text(
                        text = "Erstelle ein neues Item",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                }
                Divider(
                    modifier = Modifier.padding(8.dp)
                )

                var newName by remember { mutableStateOf("") }
                var newDescription by remember { mutableStateOf("") }

                TextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Name") },
                )

                TextField(
                    value = newDescription,
                    onValueChange = { newDescription = it },
                    label = { Text("Beschreibung") },

                )

                Button(
                    onClick = { navController.navigate("dashboard") },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(200.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Erstellen")
                }

            }
        }
    }
}