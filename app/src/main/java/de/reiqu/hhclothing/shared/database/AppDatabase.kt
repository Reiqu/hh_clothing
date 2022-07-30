package de.reiqu.hhclothing.shared.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import de.reiqu.hhclothing.shared.database.entities.Item
import de.reiqu.hhclothing.shared.database.entities.Storage
import de.reiqu.hhclothing.shared.database.interfaces.ItemDao
import de.reiqu.hhclothing.shared.database.interfaces.StorageDao


@Database(entities = [Item::class, Storage::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun storageDao(): StorageDao

    companion object {
        private val LOG_TAG = AppDatabase::class.java.simpleName
        private val LOCK = Any()
        private const val DATABASE_NAME = "hhclothing_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME)
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}