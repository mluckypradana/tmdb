package com.luc.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.luc.base.R
import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseFragment
import com.luc.base.core.base.BaseListAdapter
import com.luc.base.core.helper.json
import com.luc.base.core.listener.EndlessOnScrollListener
import com.luc.base.databinding.FragmentReviewsBinding
import com.luc.base.entity.Movie
import com.luc.base.ui.vm.ReviewsVm
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReviewsFragment : BaseFragment() {
    private lateinit var adapter: BaseListAdapter
    private val vm: ReviewsVm by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentReviewsBinding.inflate(inflater, container, false)
        bind.vm = vm
        vm.setArguments(arguments)
        vm.getReviews().observe(viewLifecycleOwner, Observer {
            adapter.items = it.data
            adapter.notifyDataSetChanged()
            bind.vfMain.displayedChild = when (it) {
                is Resource.Success -> 0
                is Resource.Loading -> 1
                is Resource.Empty -> 2
                is Resource.Error -> 3
            }
        })
        adapter = BaseListAdapter(R.layout.item_review)
        bind.rvMain.adapter = adapter
        bind.rvMain.addOnScrollListener(object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                vm.fetchReviews()
            }
        })
        return bind.root
    }

    fun setMovie(movie: Movie?) {
        vm.setMovie(movie)
    }

    companion object {
        fun newInstance(movie: Movie?): ReviewsFragment {
            val fragment = ReviewsFragment()
            val args = Bundle()
            args.putBundle(Constant.BUNDLE_DATA, bundleOf(Pair(Constant.MOVIE, movie.json())))
            fragment.arguments = args
            return fragment
        }
    }
}