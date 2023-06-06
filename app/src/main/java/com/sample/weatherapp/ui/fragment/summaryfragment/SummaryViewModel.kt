package com.sample.weatherapp.ui.fragment.summaryfragment

import android.app.Application
import com.sample.network.remote.Repository
import com.sample.weatherapp.ui.fragment.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application)
