package de.reiqu.hhclothing.shared.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey val uid: Int,
    @NonNull @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name= "image_id") val image_id: Int,
    @ColumnInfo(name= "image_url") val image_url: String,
    @ColumnInfo(name= "image_thumbnail_url") val image_thumbnail_url: String,
    @ColumnInfo(name= "image_small_url") val image_small_url: String,
    @ColumnInfo(name= "image_alt") val image_alt: String,
    @ColumnInfo(name= "updated_at") val updated_at: String,
    @ColumnInfo(name= "created_at") val created_at: String,
)


