package de.reiqu.hhclothing.shared.database.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.reiqu.hhclothing.shared.database.interfaces.StorageDao
import de.reiqu.hhclothing.shared.database.viewmodels.ItemViewModel
import de.reiqu.hhclothing.shared.database.viewmodels.StorageViewModel

class StorageViewModelFactory (
    private val storageDao: StorageDao
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StorageViewModel(storageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}