package com.luc.base.ui.vm

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.helper.toGson
import com.luc.base.entity.Genre
import com.luc.base.entity.Movie
import com.luc.base.repository.GenreRepo
import com.luc.base.repository.MovieRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class MovieVm(context: Application) : BaseViewModel(context), KoinComponent {
    var movie: MutableLiveData<Movie> = MutableLiveData()
    private val movieRepo: MovieRepo by inject()

    fun setExtras(extras: Bundle?) {
        val data = extras?.getString(Constant.BUNDLE_DATA)
        data?.let { movie.value = data.toGson(Movie::class.java) }
        fetchMovieDetail()
    }

    private fun fetchMovieDetail() = viewModelScope.launch {
        val response = movieRepo.fetchMovieDetail(movie.value)
        movie.value = response.data
    }
}