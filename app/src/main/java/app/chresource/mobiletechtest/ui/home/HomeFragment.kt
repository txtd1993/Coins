package app.chresource.mobiletechtest.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.chresource.mobiletechtest.R
import app.chresource.mobiletechtest.databinding.FragmentHomeBinding
import app.chresource.mobiletechtest.model.CurrencyModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.header.*
import java.util.*

class HomeFragment : Fragment(), CurrencyAdapter.ICurrencyListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var listCurrency: ArrayList<CurrencyModel>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.initialize(requireContext())
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        homeViewModel.getListCurrencies("USD")
        currencyAdapter = CurrencyAdapter()
        currencyAdapter.setListener(this)
        val layoutManager = LinearLayoutManager(context)
        rvCurrency.layoutManager = layoutManager
        rvCurrency.adapter = currencyAdapter
        homeViewModel.listCurrency?.observe(viewLifecycleOwner, Observer { it ->
            listCurrency = it
            currencyAdapter.setData(it)
        })
    }

    private fun initView() {
        txtTitle.text = getString(R.string.txt_list_currencies)
        ivClear.setOnClickListener {
            edtSearch.setText("")
        }
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (TextUtils.isEmpty(edtSearch.text.toString().trim())) {
                    ivClear.visibility = View.GONE
                    currencyAdapter.setData(listCurrency)
                } else {
                    ivClear.visibility = View.VISIBLE
                    doSearch()
                }
            }
        })

        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                doSearch()
                true
            } else {
                false
            }
        }
    }

    private fun doSearch() {
        val results = listCurrency.filter {
            it.name.toUpperCase(Locale.getDefault()).contains(
                edtSearch.text.toString().trim().toUpperCase(
                    Locale.getDefault()
                )
            )
        }
        currencyAdapter.setData(results as ArrayList<CurrencyModel>)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveClicked(currencyModel: CurrencyModel) {
        homeViewModel.saveCurrency(currencyModel)
    }
}