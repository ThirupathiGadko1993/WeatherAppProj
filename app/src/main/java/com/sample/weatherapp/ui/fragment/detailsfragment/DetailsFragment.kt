package com.sample.weatherapp.ui.fragment.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sample.weatherapp.databinding.FragmentDetailsBinding
import com.sample.weatherapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
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
            this?.title = args.cityName
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

    private fun initViews() {
        binding?.apply {
            tvCloudsDescription.text = args.cloudDescription
            tvTemp.text = args.temp
            tvFeelsLike.text = args.feelsLike
        }
    }

    companion object {
        const val PARAM_CITY_NAME = "city_name"
        const val PARAM_CLOUD_DESCRIPTION = "cloud_description"
        const val PARAM_TEMP = "temp"
        const val PARAM_FEELS_LIKE = "feels_like"
    }
}