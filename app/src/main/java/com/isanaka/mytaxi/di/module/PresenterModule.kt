package com.isanaka.mytaxi.di.module

import com.isanaka.mytaxi.data.repository.AppRepository
import com.isanaka.mytaxi.view.common.VehiclesContract
import com.isanaka.mytaxi.view.common.VehiclesPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun providesHomePresenter(appRepository: AppRepository): VehiclesContract.Presenter =
        VehiclesPresenter(appRepository)
}