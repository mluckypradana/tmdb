package id.co.tmdb.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import id.co.tmdb.R
import id.co.tmdb.core.base.BaseActivity
import id.co.tmdb.core.base.BaseViewPagerAdapter
import id.co.tmdb.databinding.ActivityMovieBinding
import id.co.tmdb.ui.fragment.InfoFragment
import id.co.tmdb.ui.fragment.ReviewsFragment
import id.co.tmdb.ui.vm.MovieVm
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
