package app.chresource.mobiletechtest.retrofit

import app.chresource.mobiletechtest.model.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v3/price/all_prices_for_mobile")
    fun getListCurrencies(@Query("counter_currency") counterCurrency: String): Call<CurrencyResponse>
}