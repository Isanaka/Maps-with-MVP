package com.isanaka.mytaxi.core

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds


object Config {
    var HOST = "https://fake-poi-api.mytaxi.com/"

    var HAMBURG: LatLngBounds = LatLngBounds(
        LatLng(53.394655, 10.099891),
        LatLng(53.694865, 9.757589)
    )
}