package app.chresource.mobiletechtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyModel(
    var base: String = "",
    var counter: String = "",
    var buy_price: String = "",
    var sell_price: String = "",
    var icon: String = "",
    var name: String = ""
) : Parcelable