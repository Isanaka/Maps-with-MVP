package com.isanaka.mytaxi.core

import android.content.Context
import android.support.multidex.MultiDex
import com.isanaka.mytaxi.di.component.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyTaxiApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreComponent
            .builder()
            .application(this)
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}