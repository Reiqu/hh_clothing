package de.reiqu.hhclothing.shared.database.viewmodels

import androidx.lifecycle.ViewModel
import de.reiqu.hhclothing.shared.database.entities.Storage
import de.reiqu.hhclothing.shared.database.interfaces.StorageDao
import kotlinx.coroutines.flow.Flow

class StorageViewModel(private val storageDao: StorageDao) : ViewModel() {
    fun getAllStoredItems(): Flow<List<Storage>> = storageDao.getAll()

    fun getStoredItem(item_id: Int): Flow<List<Storage>> = storageDao.getStoredItemByID(item_id)
}