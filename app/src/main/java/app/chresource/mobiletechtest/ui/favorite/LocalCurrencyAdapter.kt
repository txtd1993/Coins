package app.chresource.mobiletechtest.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.chresource.mobiletechtest.R
import app.chresource.mobiletechtest.db.entities.Currency
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.local_item_currency.view.*

class LocalCurrencyAdapter : RecyclerView.Adapter<LocalCurrencyAdapter.CurrencyHolder>() {

    private var listCurrency: ArrayList<Currency> = ArrayList()
    private var listener: ICurrencyListener? = null

    fun setListener(listener: ICurrencyListener) {
        this.listener = listener
    }

    fun remove(currency: Currency) {
        listCurrency.remove(currency)
        notifyDataSetChanged()
    }

    fun setData(listCurrency: List<Currency>) {
        this.listCurrency.clear()
        this.listCurrency.addAll(listCurrency)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.local_item_currency, parent, false)
        return CurrencyHolder(itemView)
    }

    override fun getItemCount() = listCurrency.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val ctx = holder.itemView.context
        val currencyModel = listCurrency[position]
        holder.itemView.ivRemove.setOnClickListener {
            listener?.onRemoveClicked(currencyModel, position)
        }
        holder.itemView.tvName.text = currencyModel.name
        holder.itemView.tvBase.text = currencyModel.base
        holder.itemView.tvBuy.text =
            ctx.getString(R.string.buy_format, currencyModel.buy, currencyModel.counter)
        holder.itemView.tvSell.text =
            ctx.getString(R.string.sell_format, currencyModel.sell, currencyModel.counter)
        Glide.with(ctx).load(currencyModel.icon).into(holder.itemView.ivIcon)
    }

    inner class CurrencyHolder(view: View) : RecyclerView.ViewHolder(view)

    interface ICurrencyListener {
        fun onRemoveClicked(model: Currency, position: Int)
    }
}