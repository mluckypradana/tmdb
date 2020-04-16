package id.co.tmdb.ui.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.core.base.BaseViewModel
import id.co.tmdb.entity.Genre
import id.co.tmdb.repository.GenreRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainVm(context: Application) : BaseViewModel(context), KoinComponent {
    private var genres: MutableLiveData<Resource<List<Genre>>> = MutableLiveData(Resource.Loading())
    private val genreRepo: GenreRepository by inject()

    init {
        fetchGenres()
    }

    private fun fetchGenres() = viewModelScope.launch {
        val list = genreRepo.getGenres()
        genres.value =
            if (list.isEmpty()) genreRepo.fetchGenres()
            else Resource.Success(list)
    }

    fun getGenres(): LiveData<Resource<List<Genre>>> = genres
}