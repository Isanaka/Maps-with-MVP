package com.isanaka.mytaxi.view.common

import com.google.android.gms.maps.model.LatLngBounds
import com.isanaka.mytaxi.base.BasePresenter
import com.isanaka.mytaxi.base.BaseView
import com.isanaka.mytaxi.data.model.VehicleDetailsDTO


class VehiclesContract {

    interface View : BaseView {
        fun showVehicles(vehicles: List<VehicleDetailsDTO>)
        fun showError(message: String)
        fun showInfo()
        fun onVehicleClicked(vehicleDetailsDTO: VehicleDetailsDTO)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchVehicles(latLngBounds: LatLngBounds)
        fun fetchVehicleSelected(index: Int)
    }

}