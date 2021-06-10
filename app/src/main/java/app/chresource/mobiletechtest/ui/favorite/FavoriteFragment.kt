package app.chresource.mobiletechtest.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.chresource.mobiletechtest.R
import app.chresource.mobiletechtest.databinding.FragmentHomeBinding
import app.chresource.mobiletechtest.db.entities.Currency
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.header.*

class FavoriteFragment : Fragment(), LocalCurrencyAdapter.ICurrencyListener {

    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var localCurrencyAdapter: LocalCurrencyAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.initialize(requireContext())
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getSavedCurrency()
        localCurrencyAdapter = LocalCurrencyAdapter()
        localCurrencyAdapter.setListener(this)
        val layoutManager = LinearLayoutManager(context)
        rvCurrency.layoutManager = layoutManager
        rvCurrency.adapter = localCurrencyAdapter
        viewModel.listCurrency?.observe(viewLifecycleOwner, Observer {
            localCurrencyAdapter.setData(it)
        })
    }

    private fun initView() {
        txtTitle.text = getString(R.string.title_favorites)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRemoveClicked(model: Currency, position: Int) {
        localCurrencyAdapter.remove(model)
        viewModel.removeCurrency(model)
    }
}