package com.luc.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.luc.base.R
import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseFragment
import com.luc.base.core.base.BaseListAdapter
import com.luc.base.core.control.ActivityController
import com.luc.base.core.helper.json
import com.luc.base.databinding.FragmentMoviesBinding
import com.luc.base.entity.Genre
import com.luc.base.ui.activity.MovieActivity
import com.luc.base.ui.vm.MoviesVm
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
        bind.rvMain.layoutManager = GridLayoutManager(context, 3)
        return bind.root
    }

    private fun showDetailPage(position: Int) = ActivityController.navigateTo(
        mActivity, MovieActivity::class.java, false,
        bundleOf(Constant.BUNDLE_DATA to vm.getMovieAt(position))
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