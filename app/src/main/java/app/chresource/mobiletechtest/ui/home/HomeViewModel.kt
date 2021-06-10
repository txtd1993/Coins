package app.chresource.mobiletechtest.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import app.chresource.mobiletechtest.db.AppDatabase
import app.chresource.mobiletechtest.db.entities.Currency
import app.chresource.mobiletechtest.model.CurrencyModel
import app.chresource.mobiletechtest.repository.CurrencyRepository

class HomeViewModel : ViewModel() {
    private var db: AppDatabase? = null

    var listCurrency: MutableLiveData<ArrayList<CurrencyModel>>? = null

    fun initialize(ctx: Context) {
        db = Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "my_db"
        ).allowMainThreadQueries()
            .build()
    }

    fun getListCurrencies(counterCurrency: String): MutableLiveData<ArrayList<CurrencyModel>> {
        listCurrency = CurrencyRepository.getListCurrencies(counterCurrency)
        return listCurrency as MutableLiveData<ArrayList<CurrencyModel>>
    }

    fun saveCurrency(currencyModel: CurrencyModel) {
        db?.currencyDao()?.insert(
            Currency(
                currencyModel.base,
                currencyModel.name,
                currencyModel.buy_price,
                currencyModel.sell_price,
                currencyModel.counter,
                currencyModel.icon
            )
        )
    }
}