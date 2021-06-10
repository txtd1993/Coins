package app.chresource.mobiletechtest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.chresource.mobiletechtest.R
import app.chresource.mobiletechtest.model.CurrencyModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_currency.view.*


class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    private var listCurrency: ArrayList<CurrencyModel> = ArrayList()
    private var listener: ICurrencyListener? = null

    fun setListener(listener: ICurrencyListener) {
        this.listener = listener
    }

    fun setData(listCurrency: ArrayList<CurrencyModel>) {
        this.listCurrency = listCurrency
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyHolder(itemView)
    }

    override fun getItemCount() = listCurrency.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val ctx = holder.itemView.context
        val currencyModel = listCurrency[position]
        holder.itemView.ivAdd.setOnClickListener {
            listener?.onSaveClicked(currencyModel)
        }
        holder.itemView.tvName.text = currencyModel.name
        holder.itemView.tvBase.text = currencyModel.base
        holder.itemView.tvBuy.text =
            ctx.getString(R.string.buy_format, currencyModel.buy_price, currencyModel.counter)
        holder.itemView.tvSell.text =
            ctx.getString(R.string.sell_format, currencyModel.sell_price, currencyModel.counter)
        Glide.with(ctx).load(currencyModel.icon).into(holder.itemView.ivIcon)
    }

    inner class CurrencyHolder(view: View) : RecyclerView.ViewHolder(view)

    interface ICurrencyListener {
        fun onSaveClicked(currencyModel: CurrencyModel)
    }
}