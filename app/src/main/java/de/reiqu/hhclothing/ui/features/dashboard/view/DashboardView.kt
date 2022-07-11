package de.reiqu.hhclothing.ui.features.dashboard.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.reiqu.hhclothing.error.ClothingNotFound
import de.reiqu.hhclothing.models.Item
import de.reiqu.hhclothing.models.StoredItem

class DashboardView {
    private var items: List<Item> = List(400) {Item( it, "Test $it", "Description $it")}
    private var storedItems: List<StoredItem> = List(200) { StoredItem(it, Math.random().toInt() +3) }

    private fun getClothingById(id: Int): Item {
        items.forEach { item ->
            if (item.id == id) {
                return item
            }
        }
        throw ClothingNotFound()
    }


    @Composable
    fun Dashboard() {
        ClothingList(clothes =  storedItems)
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


    @Composable
    fun ClothingElement(storedItem: StoredItem) {
        val item: Item = getClothingById(storedItem.id)

        Row (
            modifier = Modifier.padding(8.dp)
        ){
            Text(text = item.name, modifier = Modifier.padding(8.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(8.dp)) {
                Text("+")
            }

            Text(storedItem.amount.toString(), modifier = Modifier.padding(12.dp))

            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(8.dp)) {
                Text("-")
            }
        }
    }

     @Preview
     @Composable
     fun ClothingElementPreview(
         storedItem: StoredItem = storedItems[0]
     ) {
         ClothingElement(storedItem = storedItem)
     }

    @Preview (name = "ClothingListPreview")
    @Composable
    fun ClothingListPreview(
        storedItem: StoredItem = storedItems[0]
    ) {
        ClothingList(clothes = storedItems)
    }
}