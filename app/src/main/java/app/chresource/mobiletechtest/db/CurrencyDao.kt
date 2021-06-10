package app.chresource.mobiletechtest.db

import androidx.lifecycle.LiveData
import androidx.room.*
import app.chresource.mobiletechtest.db.entities.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM Currency")
    fun getAll(): LiveData<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: Currency)

    @Delete
    fun delete(currency: Currency)
}