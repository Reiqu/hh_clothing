package de.reiqu.hhclothing.shared.database.interfaces

import androidx.room.*
import de.reiqu.hhclothing.shared.database.entities.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("Select * from item")
    fun getItemsList(): Flow<List<Item>>

    @Insert
    fun insertItem(item: Item)

    @Insert
    fun insertItemBulk(vararg items: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

}