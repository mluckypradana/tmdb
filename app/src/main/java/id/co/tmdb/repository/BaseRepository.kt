package id.co.tmdb.repository

import id.co.tmdb.R
import id.co.tmdb.core.retrofit.ApiService
import id.co.tmdb.core.extension.getString
import id.co.tmdb.core.helper.SessionStorage
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseRepository : KoinComponent {
    internal val api: ApiService by inject()
    internal val sessionStorage: SessionStorage by inject()

    protected fun getGeneralErrorMessage() = getString(R.string.error_common)
}