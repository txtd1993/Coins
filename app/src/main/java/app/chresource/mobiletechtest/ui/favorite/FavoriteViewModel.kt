package app.chresource.mobiletechtest.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import app.chresource.mobiletechtest.db.AppDatabase
import app.chresource.mobiletechtest.db.entities.Currency

class FavoriteViewModel : ViewModel() {
    var listCurrency: LiveData<List<Currency>>? = null

    private var db: AppDatabase? = null

    fun initialize(ctx: Context) {
        db = Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "my_db"
        ).allowMainThreadQueries().build()
    }

    fun getSavedCurrency(): LiveData<List<Currency>> {
        listCurrency = db?.currencyDao()?.getAll()
        return listCurrency as LiveData<List<Currency>>
    }

    fun removeCurrency(currency: Currency) {
        db?.currencyDao()?.delete(currency)
    }
}