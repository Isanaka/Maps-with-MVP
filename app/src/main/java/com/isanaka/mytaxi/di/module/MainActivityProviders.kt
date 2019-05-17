package com.isanaka.mytaxi.di.module

import com.isanaka.mytaxi.view.home.VehiclesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders {
    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): VehiclesFragment

}