package com.luc.base.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseActivity
import com.luc.base.core.control.ActivityController
import com.luc.base.databinding.ActivityMainBinding
import com.luc.base.ui.vm.SplashVm
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {
    private val vm: SplashVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getGenres().observe(this, Observer {
            when(it){
                is Resource.Success-> showMainPage()
            }
        })
    }

    private fun showMainPage() {
        ActivityController.navigateTo(this, MainActivity::class.java)
        finishAffinity()
    }
}