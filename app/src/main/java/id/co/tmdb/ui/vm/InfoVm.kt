package id.co.tmdb.ui.vm

import android.app.Application
import android.os.Bundle
import id.co.tmdb.R
import id.co.tmdb.core.Constant
import id.co.tmdb.core.base.BaseViewModel
import id.co.tmdb.core.extension.getAppContext
import id.co.tmdb.core.helper.DateHelper
import id.co.tmdb.core.extension.toGson
import id.co.tmdb.entity.Movie
import org.koin.core.KoinComponent

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