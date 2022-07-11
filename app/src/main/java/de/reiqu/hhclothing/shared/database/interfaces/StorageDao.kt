package de.reiqu.hhclothing.shared.database.interfaces

import androidx.room.*
import de.reiqu.hhclothing.models.Item
import de.reiqu.hhclothing.models.db.Storage

@Dao
interface StorageDao {
    @Insert
    fun insertAll(vararg storages: Storage)

    @Delete
    fun delete(storage: Storage)

    @Update
    fun updateCount(item: Item, count: Int)

    @Query("SELECT * FROM storage")
    fun getAll(): List<Storage>

    @Query("SELECT * FROM storage JOIN item ON storage.item_id = item.uid")
    fun getStorage(): Map<Storage, List<Item>>

}

data class StorageItemRelation (val item: Item, val storage: Storage)