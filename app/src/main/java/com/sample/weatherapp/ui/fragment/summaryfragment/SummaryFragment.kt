package com.sample.weatherapp.ui.fragment.summaryfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.sample.data.ListItem
import com.sample.weatherapp.adapter.SummaryRecyclerViewAdapter
import com.sample.weatherapp.databinding.FragmentSummaryBinding
import com.sample.weatherapp.listener.OnRecyclerViewItemClick
import com.sample.weatherapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : BaseFragment(), OnRecyclerViewItemClick {

    private val args by navArgs<SummaryFragmentArgs>()
    private val viewModel by viewModels<SummaryViewModel>()
    private var binding: FragmentSummaryBinding? = null

    private val summaryList = mutableListOf<ListItem?>()
    private val adapter = SummaryRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        with((activity as AppCompatActivity).supportActionBar) {
            this?.title = args.weatherResponse.city?.name.orEmpty()
            this?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        args.weatherResponse.list?.getOrNull(position)?.let { listItem ->
            listItem.weather?.firstOrNull()?.let { weatherItem ->
                navigationManager?.navigateToDetailsFragment(
                    cityName = args.weatherResponse.city?.name.orEmpty(),
                    listItem = listItem,
                    weatherItem = weatherItem
                )
            }
        }
    }

    private fun initViews() {
        summaryList.clear()
        args.weatherResponse.list?.let { listItem ->
            summaryList.addAll(listItem)
            binding?.rvSummary?.apply {
                adapter = this@SummaryFragment.adapter.apply {
                    setData(listItem.map { it.toSummaryItem() })
                }
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    companion object {
        const val PARAM_WEATHER_RESPONSE = "weather_response"
    }
}