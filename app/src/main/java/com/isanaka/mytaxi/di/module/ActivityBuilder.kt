package com.isanaka.mytaxi.di.module

import com.isanaka.mytaxi.view.home.MainActivity
import com.isanaka.mytaxi.view.maps.VehiclesMapActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMapsActivity(): VehiclesMapActivity
}