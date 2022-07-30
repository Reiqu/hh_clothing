package de.reiqu.hhclothing

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import de.reiqu.hhclothing.shared.api.controller.ApiController
import de.reiqu.hhclothing.shared.api.controller.VolleyCallback
import de.reiqu.hhclothing.shared.database.AppDatabase
import de.reiqu.hhclothing.shared.database.AppExecutors
import de.reiqu.hhclothing.shared.database.entities.Item
import de.reiqu.hhclothing.ui.features.clothing.DetailScreen
import de.reiqu.hhclothing.ui.features.clothing.administration.add.AddScreen
import de.reiqu.hhclothing.ui.features.clothing.administration.list.ListScreen
import de.reiqu.hhclothing.ui.features.dashboard.DashboardView
import de.reiqu.hhclothing.ui.theme.HHClothingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Toast.makeText(this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fetch failed",
                        Toast.LENGTH_SHORT).show()
                }
            }

        val api = ApiController(remoteConfig)



        super.onCreate(savedInstanceState)
        setContent {
            HHClothingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    val ctx = LocalContext.current
                    val queue =  Volley.newRequestQueue(ctx)

                    val storageRequest = api.getItemStore(ctx)
                    val itemRequest = api.getItems(ctx)
                    queue.add(storageRequest)
                    queue.add(itemRequest)

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

