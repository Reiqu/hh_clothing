package de.reiqu.hhclothing.ui.features.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.reiqu.hhclothing.R
import de.reiqu.hhclothing.error.ClothingNotFound
import de.reiqu.hhclothing.models.Item
import de.reiqu.hhclothing.models.StoredItem

class DashboardView(private val navController: NavController? = null) {


    private var items: List<Item> = List(400) {Item( it, "Test $it", "Description $it")}
    private var storedItems: List<StoredItem> = List(200) { StoredItem(it, (0..10).random()) }

    private fun getClothingById(id: Int): Item {
        items.forEach { item ->
            if (item.id == id) {
                return item
            }
        }
        throw ClothingNotFound()
    }

    @Preview
    @Composable
    fun Dashboard() {
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
                        onClick = { navController!!.navigate("clothing_list") },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_list), contentDescription = "List")
                    }
                    Button(
                        onClick = { navController!!.navigate("clothing_add") },
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
    fun ClothingList(clothes: List<StoredItem>) {
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


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ClothingElement(storedItem: StoredItem) {
        var amount: Int by remember {
            mutableStateOf(storedItem.amount)
        }

        val item: Item by remember {
            mutableStateOf(getClothingById(storedItem.id))
        }

        ElevatedCard( modifier = Modifier
            .padding(8.dp),
        ) {
            Row (
                modifier = Modifier
                    .clickable { navController!!.navigate("clothing_details") }
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = item.name,
                    modifier = Modifier.padding(8.dp)
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

     @Composable
     fun ClothingElementPreview(
         storedItem: StoredItem = storedItems[0]
     ) {
         ClothingElement(storedItem = storedItem)
     }

    @Composable
    fun ClothingListPreview(
        storedItem: StoredItem = storedItems[0]
    ) {
        ClothingList(clothes = storedItems)
    }
}