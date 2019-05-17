package com.isanaka.mytaxi.data.network

import com.isanaka.mytaxi.data.model.VehicleDetailsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?")
    fun getVehicles(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Observable<VehicleDetailsResponse>

}