package com.isanaka.mytaxi.data.repository

import com.google.android.gms.maps.model.LatLngBounds
import com.isanaka.mytaxi.data.model.VehicleDetailsResponse
import com.isanaka.mytaxi.data.network.ApiDisposable
import com.isanaka.mytaxi.data.network.ApiError
import com.isanaka.mytaxi.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AppRepoImp(
    private val apiService: ApiService
) : AppRepository {
    override fun getVehicles(
        latLngBounds: LatLngBounds,
        success: (VehicleDetailsResponse) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {
        return apiService
            .getVehicles(
                latLngBounds.southwest.latitude,
                latLngBounds.southwest.longitude,
                latLngBounds.northeast.latitude,
                latLngBounds.northeast.longitude
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<VehicleDetailsResponse>(
                    {

                        success(it)
                    },
                    failure
                )
            )
    }

}