package com.isanaka.mytaxi.view.home

import android.os.Bundle
import com.isanaka.mytaxi.R
import com.isanaka.mytaxi.base.BaseActivity
import com.isanaka.mytaxi.core.openActivity
import com.isanaka.mytaxi.data.model.VehicleDetailsDTO
import com.isanaka.mytaxi.view.maps.VehiclesMapActivity

class MainActivity : BaseActivity(), RoutingHelper {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(VehiclesFragment())
    }

    override fun onLocateVehicleOnMap(vehicleDetailsDTO: VehicleDetailsDTO) {
        val bundle = Bundle()
        bundle.putParcelable(getString(R.string.selectedVehicle), vehicleDetailsDTO)
        openActivity(VehiclesMapActivity::class.java, bundle)
    }

}
