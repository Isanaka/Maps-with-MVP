package com.isanaka.mytaxi.data.repository

import com.google.android.gms.maps.model.LatLngBounds
import com.isanaka.mytaxi.data.model.VehicleDetailsResponse
import com.isanaka.mytaxi.data.network.ApiError
import io.reactivex.disposables.Disposable

interface AppRepository {

    fun getVehicles(
        latLngBounds: LatLngBounds,
        success: (VehicleDetailsResponse) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable
}