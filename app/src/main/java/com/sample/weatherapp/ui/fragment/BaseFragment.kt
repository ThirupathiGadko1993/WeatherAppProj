package com.sample.weatherapp.ui.fragment

import androidx.fragment.app.Fragment
import com.sample.weatherapp.NavigationManager
import javax.inject.Inject

open class BaseFragment : Fragment() {
    var navigationManager: NavigationManager? = null
        @Inject set
}