package com.luc.base.ui.vm

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luc.base.R
import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.helper.DateHelper
import com.luc.base.core.helper.toGson
import com.luc.base.entity.Genre
import com.luc.base.entity.Movie
import com.luc.base.repository.GenreRepo
import com.luc.base.repository.MovieRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class InfoVm(context: Application) : BaseViewModel(context), KoinComponent {
    var movie: Movie? = null

    fun setArguments(arguments: Bundle?) {
        val data = arguments?.getBundle(Constant.BUNDLE_DATA)
        movie = data?.getString(Constant.MOVIE)?.toGson(Movie::class.java)
    }

    fun getGenres(): String {
        var genre = ""
        movie?.genres?.forEach {
            genre += it.name + ", "
        }
        if (genre.length > 2) genre.substring(0, genre.length - 3)
        return genre
    }

    fun getReleaseDate(): String? {
        return DateHelper.reformat(movie?.releaseDate, Constant.DEFAULT_DATE_INPUT, Constant.DEFAULT_DATE_OUTPUT)
    }

    fun getDuration(): String? {
        return getAppContext().getString(R.string.label_runtime_value, movie?.runtime.toString())
    }

    fun getTailerVideoKey(): String? {
        return movie?.videos?.results?.get(0)?.key
    }
}