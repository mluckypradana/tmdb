package id.co.tmdb.core.module

import com.google.gson.Gson
import id.co.tmdb.core.retrofit.ApiService
import id.co.tmdb.core.retrofit.RetrofitFactory
import org.koin.dsl.module

object NetworkModule {
    val networkModule = module {
        single { provideGson() }
        single { RetrofitFactory.retrofitService() }
    }
    val networkMockModule = module {
        single { provideGson() }
        single { provideApiService() }
    }

    private fun provideApiService(): ApiService {
        return RetrofitFactory.retrofitService()
    }

    private fun provideGson(): Gson? {
        return Gson().newBuilder().create()
    }
}
