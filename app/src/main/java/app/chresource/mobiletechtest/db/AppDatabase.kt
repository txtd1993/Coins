package app.chresource.mobiletechtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.chresource.mobiletechtest.db.entities.Currency

@Database(entities = [Currency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}