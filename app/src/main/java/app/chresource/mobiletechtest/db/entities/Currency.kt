package app.chresource.mobiletechtest.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey val base: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "buy") val buy: String?,
    @ColumnInfo(name = "sell") val sell: String?,
    @ColumnInfo(name = "counter") val counter: String?,
    @ColumnInfo(name = "icon") var icon: String?,
)