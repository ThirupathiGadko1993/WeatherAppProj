package com.sample.weatherapp.ui.fragment.homefragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.sample.weatherapp.R
import com.sample.weatherapp.databinding.FragmentHomeBinding
import com.sample.weatherapp.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        with((activity as AppCompatActivity).supportActionBar) {
            this?.title = getString(R.string.app_name)
            this?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initListeners() {
        binding?.btnSearch?.setOnClickListener {
            viewModel.fetchWeatherReport(binding?.etSearch?.text?.toString().orEmpty())
        }

        binding?.etSearch?.addTextChangedListener {
            updateButtonState()
        }
    }

    private fun initObservers() {
        with(viewModel) {
            response.observe(viewLifecycleOwner) {
                it?.let {
                    navigationManager?.navigateToSummaryFragment(it)
                } ?: run {
                    showErrorAlertDialog(getString(R.string.not_found))
                }
            }

            loading.observe(viewLifecycleOwner) { isLoading ->
                with(binding) {
                    this?.progress?.isVisible = isLoading
                    this?.etSearch?.isVisible = !isLoading
                }
                updateButtonState()
            }
        }
    }

    private fun showErrorAlertDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun updateButtonState() {
        binding?.btnSearch?.isEnabled =
            viewModel.loading.value == false &&
                    binding?.etSearch?.text?.toString().isNullOrBlank().not()
    }
}
