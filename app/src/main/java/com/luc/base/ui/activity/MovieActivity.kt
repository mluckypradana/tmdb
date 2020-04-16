package com.luc.base.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.luc.base.R
import com.luc.base.core.base.BaseActivity
import com.luc.base.core.base.BaseViewPagerAdapter
import com.luc.base.databinding.ActivityMovieBinding
import com.luc.base.ui.fragment.InfoFragment
import com.luc.base.ui.fragment.ReviewsFragment
import com.luc.base.ui.vm.MovieVm
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : BaseActivity() {
    private lateinit var infoFragment: InfoFragment
    private lateinit var reviewsFragment: ReviewsFragment
    private lateinit var adapter: BaseViewPagerAdapter
    private lateinit var bind: ActivityMovieBinding
    private val vm: MovieVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMovieBinding.inflate(layoutInflater)
        bind.vm = vm
        vm.setExtras(intent.extras)
        setupTab()
        vm.movie.observe(this, Observer {
            when {
                !it.genres.isNullOrEmpty() -> {
                    infoFragment.setMovie(it)
                    reviewsFragment.setMovie(it)
                }
            }
        })
        setSupportActionBar(bind.toolbar)
        setContentView(bind.root)
    }

    private fun setupTab() {
        bind.tlMain.setupWithViewPager(bind.vpMain)
        adapter = BaseViewPagerAdapter(supportFragmentManager)
        infoFragment = InfoFragment.newInstance(vm.movie.value)
        reviewsFragment = ReviewsFragment.newInstance(vm.movie.value)
        adapter.addFragment(infoFragment, getString(R.string.label_info))
        adapter.addFragment(reviewsFragment, getString(R.string.label_review))
        bind.vpMain.adapter = adapter
    }
}
