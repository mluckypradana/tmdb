package id.co.tmdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import id.co.tmdb.R
import id.co.tmdb.core.Constant
import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.core.base.BaseFragment
import id.co.tmdb.core.base.BaseListAdapter
import id.co.tmdb.core.control.ActivityController
import id.co.tmdb.core.extension.json
import id.co.tmdb.core.listener.EndlessOnScrollListener
import id.co.tmdb.databinding.FragmentMoviesBinding
import id.co.tmdb.entity.Genre
import id.co.tmdb.ui.activity.MovieActivity
import id.co.tmdb.ui.vm.MoviesVm
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : BaseFragment() {
    private lateinit var adapter: BaseListAdapter
    private val vm: MoviesVm by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentMoviesBinding.inflate(inflater, container, false)
        bind.vm = vm
        vm.setArguments(arguments)
        vm.getMovies().observe(viewLifecycleOwner, Observer {
            adapter.items = it.data
            adapter.notifyDataSetChanged()
            bind.vfMain.displayedChild = when (it) {
                is Resource.Success -> 0
                is Resource.Loading -> 1
                is Resource.Empty -> 2
                is Resource.Error -> 3
            }
        })
        adapter = BaseListAdapter(R.layout.item_movie)
        adapter.onItemClick = { _, position ->
            showDetailPage(position)
        }
        bind.rvMain.adapter = adapter
        bind.rvMain.addOnScrollListener(object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                vm.fetchMovies()
            }
        })
        return bind.root
    }

    private fun showDetailPage(position: Int) = ActivityController.navigateTo(
        mActivity, MovieActivity::class.java, false,
        bundleOf(Constant.BUNDLE_DATA to vm.getMovieAt(position).json())
    )

    companion object {
        fun newInstance(genre: Genre): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putBundle(Constant.BUNDLE_DATA, bundleOf(Pair(Constant.GENRE, genre.json())))
            fragment.arguments = args
            return fragment
        }
    }
}