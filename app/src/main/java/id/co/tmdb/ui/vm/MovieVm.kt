package id.co.tmdb.ui.vm

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.co.tmdb.core.Constant
import id.co.tmdb.core.base.BaseViewModel
import id.co.tmdb.core.extension.toGson
import id.co.tmdb.entity.Movie
import id.co.tmdb.repository.MovieRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

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