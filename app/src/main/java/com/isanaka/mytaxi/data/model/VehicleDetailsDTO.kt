package com.isanaka.mytaxi.data.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VehicleDetailsDTO(
    val id: Int,
    val coordinate: LatLng,
    val fleetType: String,
    val heading: Double,
    val direction: String
) : Parcelable