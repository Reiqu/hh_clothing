package de.reiqu.hhclothing.shared.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage")
data class Storage(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "item_id") val item_id: Int,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "updated_at") val updated_at: String,
)