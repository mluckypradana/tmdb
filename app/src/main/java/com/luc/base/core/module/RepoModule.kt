package com.luc.base.core.module

import com.luc.base.core.helper.SessionStorage
import com.luc.base.repository.GenreRepo
import com.luc.base.repository.MovieRepo
import com.luc.base.repository.ReviewRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object RepoModule {
    val dbModule = module {
        single { SessionStorage(androidContext()) }
        single { GenreRepo() }
        single { MovieRepo() }
        single { ReviewRepo() }
    }
}
