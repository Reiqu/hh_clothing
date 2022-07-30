package de.reiqu.hhclothing.shared.database.interfaces

import androidx.room.*
import de.reiqu.hhclothing.shared.database.entities.Item
import de.reiqu.hhclothing.shared.database.entities.Storage
import kotlinx.coroutines.flow.Flow

@Dao
interface StorageDao {
    @Insert
    fun insertAll(vararg storages: Storage)

    @Delete
    fun delete(storage: Storage)

    @Update
    fun updateCount(storage: Storage)

    @Query("SELECT * FROM storage")
    fun getAll(): Flow<List<Storage>>

    @Query("SELECT * FROM storage WHERE storage.item_id = :itemId")
    fun getStoredItemByID(itemId: Int): Flow<List<Storage>>

}

data class StorageItemRelation (val item: Item, val storage: Storage)