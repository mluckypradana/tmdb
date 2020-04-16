package com.luc.base.ui.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luc.base.core.retrofit.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.entity.Genre
import com.luc.base.repository.GenreRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainVm(context: Application) : BaseViewModel(context), KoinComponent {
    private var genres: MutableLiveData<Resource<List<Genre>>> = MutableLiveData(Resource.Loading())
    private val genreRepo: GenreRepo by inject()

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