package com.luc.base.ui.vm

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luc.base.core.Constant
import com.luc.base.core.retrofit.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.toGson
import com.luc.base.entity.Movie
import com.luc.base.entity.Review
import com.luc.base.repository.ReviewRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ReviewsVm(context: Application) : BaseViewModel(context), KoinComponent {
    private var movie: Movie? = null
    private var page: Int = 0
    private var reviews: MutableLiveData<Resource<MutableList<Review>>> = MutableLiveData(Resource.Loading())
    private val repo: ReviewRepo by inject()

    fun fetchReviews(refresh: Boolean = false) = viewModelScope.launch {
        val list = reviews.value?.data ?: arrayListOf()
        if (!refresh)
            page++
        else {
            page = 1
            list.clear()
        }
        when (val resource = repo.fetchReviews(page, movie)) {
            is Resource.Success -> {
                val mutableList = resource.data?.toMutableList()
                if (mutableList.isNullOrEmpty() && refresh)
                    reviews.value = Resource.Empty()
                else {
                    mutableList?.let { list.addAll(it) }
                    reviews.value = Resource.Success(list)
                }
            }
            else -> reviews.value = resource
        }
    }

    fun getReviews(): LiveData<Resource<MutableList<Review>>> = reviews

    fun setArguments(arguments: Bundle?) {
        val data = arguments?.getBundle(Constant.BUNDLE_DATA)
        movie = data?.getString(Constant.MOVIE)?.toGson(Movie::class.java)
        fetchReviews()
    }

    fun setMovie(movie: Movie?) {
        this.movie = movie
    }
}