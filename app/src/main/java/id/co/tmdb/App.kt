package id.co.tmdb

import androidx.multidex.MultiDexApplication
import id.co.tmdb.core.module.RepoModule.dbModule
import id.co.tmdb.core.module.NetworkModule.networkModule
import id.co.tmdb.core.module.VmModule.vmModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()

        startKoin {
            androidContext(this@App)
            modules(networkModule)
            modules(vmModule)
            modules(dbModule)
        }
    }
}