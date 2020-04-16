package com.luc.base.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseActivity
import com.luc.base.core.base.BaseViewPagerAdapter
import com.luc.base.entity.Genre
import com.luc.base.databinding.ActivityMainBinding
import com.luc.base.ui.fragment.MoviesFragment
import com.luc.base.ui.vm.MainVm
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var adapter: BaseViewPagerAdapter
    private lateinit var bind: ActivityMainBinding
    private val vm: MainVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        bind.vm = vm
        setSupportActionBar(bind.toolbar)
        vm.getGenres().observe(this, Observer {
            when (it) {
                is Resource.Success -> addTab(it.data)
            }
        })
        setContentView(bind.root)
    }

    private fun addTab(data: List<Genre>?) {
        bind.tlMain.setupWithViewPager(bind.vpMain)
        adapter = BaseViewPagerAdapter(supportFragmentManager)
        data?.forEach {
            adapter.addFragment(MoviesFragment.newInstance(it), it.name)
        }
        bind.vpMain.adapter = adapter
    }
}
