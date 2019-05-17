package com.isanaka.mytaxi.di.component

import android.app.Application
import com.isanaka.mytaxi.core.MyTaxiApp
import com.isanaka.mytaxi.di.module.ActivityBuilder
import com.isanaka.mytaxi.di.module.NetworkModule
import com.isanaka.mytaxi.di.module.PresenterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ActivityBuilder::class,
        PresenterModule::class]
)
interface CoreComponent : AndroidInjector<MyTaxiApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }
}