package com.isanaka.mytaxi.view.home

import com.isanaka.mytaxi.data.model.VehicleDetailsDTO

interface RoutingHelper {
    fun onLocateVehicleOnMap(vehicleDetailsDTO: VehicleDetailsDTO)
}