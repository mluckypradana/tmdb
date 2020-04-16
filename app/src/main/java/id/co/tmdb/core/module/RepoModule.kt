package id.co.tmdb.core.module

import id.co.tmdb.core.helper.SessionStorage
import id.co.tmdb.repository.GenreRepository
import id.co.tmdb.repository.MovieRepo
import id.co.tmdb.repository.ReviewRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object RepoModule {
    val dbModule = module {
        single { SessionStorage(androidContext()) }
        single { GenreRepository() }
        single { MovieRepo() }
        single { ReviewRepo() }
    }
}
