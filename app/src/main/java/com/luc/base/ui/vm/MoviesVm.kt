package com.luc.base.ui.vm

import android.app.Application
import android.os.Bundle
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

class MoviesVm(context: Application) : BaseViewModel(context), KoinComponent {
    private var genre: Genre? = null
    private var movies: MutableLiveData<Resource<List<Movie>>> = MutableLiveData(Resource.Loading())
    private val repo: MovieRepo by inject()

    init {
        fetchMovies()
    }

    private fun fetchMovies() = viewModelScope.launch {
        movies.value = repo.fetchMoviesByGenre(genre)
    }

    fun getMovies(): LiveData<Resource<List<Movie>>> = movies

    fun setArguments(arguments: Bundle?) {
        val data = arguments?.getBundle(Constant.BUNDLE_DATA)
        genre = data?.getString(Constant.GENRE)?.toGson(Genre::class.java)
    }

    fun getMovieAt(position: Int): Movie? {
        return movies.value?.data?.get(position)
    }
}