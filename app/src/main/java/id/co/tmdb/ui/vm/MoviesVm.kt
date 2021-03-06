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
import id.co.tmdb.entity.Genre
import id.co.tmdb.entity.Movie
import id.co.tmdb.repository.MovieRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesVm(context: Application) : BaseViewModel(context), KoinComponent {
    private var genre: Genre? = null
    private var page: Int = 0
    private var movies: MutableLiveData<Resource<MutableList<Movie>>> = MutableLiveData(Resource.Loading())
    private val repo: MovieRepo by inject()

    fun fetchMovies(refresh: Boolean = false) = viewModelScope.launch {
        val list = movies.value?.data ?: arrayListOf()
        if (!refresh)
            page++
        else {
            page = 1
            list.clear()
        }
        when (val resource = repo.fetchMoviesByGenre(page, genre)) {
            is Resource.Success -> {
                val mutableList = resource.data?.toMutableList()
                if (mutableList.isNullOrEmpty() && refresh)
                    movies.value = Resource.Empty()
                else {
                    mutableList?.let { list.addAll(it) }
                    movies.value = Resource.Success(list)
                }
            }
            else -> movies.value = resource
        }
    }

    fun getMovies(): LiveData<Resource<MutableList<Movie>>> = movies

    fun setArguments(arguments: Bundle?) {
        val data = arguments?.getBundle(Constant.BUNDLE_DATA)
        genre = data?.getString(Constant.GENRE)?.toGson(Genre::class.java)
        fetchMovies()
    }

    fun getMovieAt(position: Int): Movie? {
        return movies.value?.data?.get(position)
    }

}