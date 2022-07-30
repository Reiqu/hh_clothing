package de.reiqu.hhclothing.ui.features.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.reiqu.hhclothing.R
import de.reiqu.hhclothing.error.ClothingNotFound
import de.reiqu.hhclothing.shared.database.entities.Item
import de.reiqu.hhclothing.shared.database.entities.Storage

class DashboardView(private val navController: NavController? = null) {

    private val items: List<Item> = List(400) {
        Item(
            uid = it,
            name = "Test $it",
            description = "Description $it",
            image_url = "https://via.placeholder.com/150",
            created_at = "2022-07-30",
            updated_at = "2022-07-30",
            image_small_url = "https://via.placeholder.com/150",
            image_id = 2,
            image_alt = "Placeholder" ,
            image_thumbnail_url = "https://via.placeholder.com/150")
    }

    private fun getClothingById(id: Int): Item {
        items.forEach { item ->
            if (item.uid == id) {
                return item
            }
        }
        throw ClothingNotFound()
    }

    @Composable
    fun Dashboard() {

        val storedItems: List<Storage> by remember {
            mutableStateOf(List(200) { Storage(
                uid =  it,
                amount =  (0..10).random(),
                updated_at = "2022-07-30",
                created_at = "2022-07-30",
                item_id = 2
            ) })
        }

        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
                ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                Text(
                    text = "Dashboard",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 32.sp)
                )
                Row {
                    Button(
                        onClick = { navController!!.navigate("clothing_list") {popUpTo("dashboard")} },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_list), contentDescription = "List")
                    }
                    Button(
                        onClick = { navController!!.navigate("clothing_add") {popUpTo("dashboard")} },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add")
                    }
                }
            }

            ClothingList(clothes =  storedItems)
        }

    }

    @Composable
    fun ClothingList(clothes: List<Storage>) {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
        ) {
            items(items = clothes) {
                ClothingElement(storedItem = it)
            }
        }
    }


    @Composable
    fun ClothingElement(storedItem: Storage) {
        var amount: Int by remember {
            mutableStateOf(storedItem.amount)
        }

        val item: Item by remember {
            mutableStateOf(getClothingById(storedItem.uid))
        }

        ElevatedCard( modifier = Modifier
            .padding(8.dp),
        ) {
            Row (
                modifier = Modifier
                    .clickable { navController!!.navigate("clothing_details") {popUpTo("dashboard")} }
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = item.name.toString(),
                    modifier = Modifier
                        .padding(8.dp)
                        .widthIn(130.dp, 200.dp)
                )

                Button(
                    onClick = { amount++ },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add")
                }

                Text(text = amount.toString(), modifier = Modifier.padding(12.dp))

                Button(onClick = { amount-- }, modifier = Modifier.padding(8.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_remove), contentDescription = "Remove")

                }
            }
        }
    }
}
