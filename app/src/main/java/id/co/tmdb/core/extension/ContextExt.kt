package id.co.tmdb.core.extension

import android.app.Application
import org.koin.core.context.GlobalContext

fun getAppContext() = GlobalContext.get().koin.get<Application>()
fun getString(id: Int) = getAppContext().getString(id)