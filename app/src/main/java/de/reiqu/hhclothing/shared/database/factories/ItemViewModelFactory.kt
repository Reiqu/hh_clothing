package de.reiqu.hhclothing.shared.database.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.reiqu.hhclothing.shared.database.interfaces.ItemDao
import de.reiqu.hhclothing.shared.database.viewmodels.ItemViewModel

class ItemViewModelFactory (
    private val itemDao: ItemDao
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}