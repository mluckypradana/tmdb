package id.co.tmdb.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.core.base.BaseActivity
import id.co.tmdb.core.control.ActivityController
import id.co.tmdb.ui.vm.SplashVm
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