package de.reiqu.hhclothing.ui.features.clothing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.reiqu.hhclothing.R
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.lang.RuntimeException

class DetailScreen(private val navController: NavController) {

    @Composable
    fun DetailScreenView() {
        Surface {
            Column {
//                Button(onClick = { navController.navigate("dashboard") }) {
//                    Text("Zurück")
//                }

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://cms.reiqu.de/uploads/medium_greenday_revolutionradiocover_back_lg_3092742ab5.webp")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.mipmap.placeholder),
                    contentDescription = "Bild",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(8.dp)
                )

                Divider()
                Text(text = "Titel")
                Text(text = "Beschreibung")
                Text(text = "Derzeitig in Hamburg:")
                Text(text = "0")
                
                Divider()
                Button(
                    onClick = { throw RuntimeException("Test Crash") }) {
                    Text(text = "Test Crash")
                }
            }
        }
    }
}