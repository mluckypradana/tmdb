package id.co.tmdb.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.core.base.BaseActivity
import id.co.tmdb.core.base.BaseViewPagerAdapter
import id.co.tmdb.entity.Genre
import id.co.tmdb.databinding.ActivityMainBinding
import id.co.tmdb.ui.fragment.MoviesFragment
import id.co.tmdb.ui.vm.MainVm
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
