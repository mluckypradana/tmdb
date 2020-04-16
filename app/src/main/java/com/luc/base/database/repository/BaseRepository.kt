package com.luc.base.database.repository

import com.luc.base.R
import com.luc.base.core.api.ApiService
import com.luc.base.core.extension.getString
import com.luc.base.core.helper.SessionStorage
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.UnknownHostException


open class BaseRepository : KoinComponent {
    internal val api: ApiService by inject()
    internal val sessionStorage: SessionStorage by inject()

    protected fun getNoConnectionMessage() = getString(R.string.message_no_connection)
}