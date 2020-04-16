package com.luc.base

import androidx.multidex.MultiDexApplication
import com.luc.base.core.module.RepoModule.dbModule
import com.luc.base.core.module.NetworkModule.networkModule
import com.luc.base.core.module.VmModule.vmModule
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