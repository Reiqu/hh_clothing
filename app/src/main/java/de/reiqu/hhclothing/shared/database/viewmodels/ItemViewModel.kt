package de.reiqu.hhclothing.shared.database.viewmodels

import androidx.lifecycle.ViewModel
import de.reiqu.hhclothing.shared.database.entities.Item
import de.reiqu.hhclothing.shared.database.interfaces.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemViewModel(private val itemDao: ItemDao): ViewModel() {

    fun getAllItems(): Flow<List<Item>> = itemDao.getItemsList()


}