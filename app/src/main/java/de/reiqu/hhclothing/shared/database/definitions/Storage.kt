package de.reiqu.hhclothing.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage")
data class Storage(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "item_id") val item_id: Int,
    @ColumnInfo(name = "count") val count: Int,
)