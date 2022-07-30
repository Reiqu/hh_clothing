package de.reiqu.hhclothing

import android.app.Application
import de.reiqu.hhclothing.shared.database.AppDatabase

class HHClothingApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }


}