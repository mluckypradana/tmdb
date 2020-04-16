package id.co.tmdb.ui.vm

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.co.tmdb.core.Constant
import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.core.base.BaseViewModel
import id.co.tmdb.core.extension.toGson
import id.co.tmdb.entity.Movie
import id.co.tmdb.entity.Review
import id.co.tmdb.repository.ReviewRepo
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