package com.isanaka.mytaxi.data.model

import com.google.android.gms.maps.model.LatLng

data class VehicleDetails(
    val id: Int,
    val coordinate: LatLng,
    val fleetType: String,
    val heading: Double
)
