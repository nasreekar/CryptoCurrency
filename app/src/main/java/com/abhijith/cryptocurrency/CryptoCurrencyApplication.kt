package com.abhijith.cryptocurrency

import android.app.Application
import com.abhijith.cryptocurrency.di.appModule
import com.abhijith.data.di.dataModule
import com.abhijith.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoCurrencyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoCurrencyApplication)
            modules(
                domainModule,
                dataModule,
                appModule
            )
        }
    }
}