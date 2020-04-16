package com.luc.base.core.module

import com.luc.base.core.base.BaseViewModel
import com.luc.base.ui.home.HomeVm
import com.luc.base.ui.login.LoginVm
import com.luc.base.ui.notes.NotesVm
import com.luc.base.ui.notify.NotifyVm
import com.luc.base.ui.vm.MainVm
import com.luc.base.ui.vm.MoviesVm
import com.luc.base.ui.vm.SplashVm
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VmModule {
    val vmModule = module {
        viewModel { BaseViewModel(androidApplication()) }
        viewModel { MainVm(androidApplication()) }
        viewModel { SplashVm(androidApplication()) }
        viewModel { MoviesVm(androidApplication()) }

        viewModel { LoginVm(androidApplication()) }
        viewModel { HomeVm(androidApplication()) }
        viewModel { NotesVm(androidApplication()) }
        viewModel { NotifyVm(androidApplication()) }
    }
}
