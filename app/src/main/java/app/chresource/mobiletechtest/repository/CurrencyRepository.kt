package app.chresource.mobiletechtest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.chresource.mobiletechtest.model.CurrencyModel
import app.chresource.mobiletechtest.model.CurrencyResponse
import app.chresource.mobiletechtest.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CurrencyRepository {
    var listCurrency = MutableLiveData<ArrayList<CurrencyModel>>()
    fun getListCurrencies(counterCurrency: String): MutableLiveData<ArrayList<CurrencyModel>> {
        val call = RetrofitClient.apiInterface.getListCurrencies(counterCurrency)
        call.enqueue(object : Callback<CurrencyResponse> {
            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                if (response.isSuccessful) {
                    val dataResponse = response.body()
                    listCurrency.value = dataResponse?.data
                } else {
                    Log.v("DEBUG : ", "error")
                }

            }

            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
        return listCurrency

    }
}