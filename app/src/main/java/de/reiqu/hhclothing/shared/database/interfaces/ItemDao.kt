package de.reiqu.hhclothing.shared.database.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.reiqu.hhclothing.models.Item

@Dao
interface ItemDao {
    @Insert
    fun insertAll(vararg items: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

}