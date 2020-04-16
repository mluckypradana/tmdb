package com.luc.base.core.module

import com.luc.base.core.base.BaseViewModel
import com.luc.base.ui.vm.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VmModule {
    val vmModule = module {
        viewModel { BaseViewModel(androidApplication()) }
        viewModel { MainVm(androidApplication()) }
        viewModel { SplashVm(androidApplication()) }
        viewModel { MoviesVm(androidApplication()) }
        viewModel { MovieVm(androidApplication()) }
        viewModel { InfoVm(androidApplication()) }
        viewModel { ReviewsVm(androidApplication()) }
    }
}
