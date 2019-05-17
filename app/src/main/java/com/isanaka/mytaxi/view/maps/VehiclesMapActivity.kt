package com.isanaka.mytaxi.view.maps

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.isanaka.mytaxi.R
import com.isanaka.mytaxi.base.BaseActivity
import com.isanaka.mytaxi.data.model.VehicleDetailsDTO
import com.isanaka.mytaxi.view.common.VehiclesContract
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject


class VehiclesMapActivity : BaseActivity(), VehiclesContract.View,
    OnMapReadyCallback,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnCameraMoveStartedListener {

    @Inject
    lateinit var presenter: VehiclesContract.Presenter
    private lateinit var map: GoogleMap
    private var moveStarted = false
    private var vehicleDetailsDTO: VehicleDetailsDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeMapFragment()
        pickVehicleDetailsFromIntent()
        presenter.attachView(this)
    }

    private fun initializeMapFragment() {
        val supportMapFragment = SupportMapFragment.newInstance()
        replaceFragment(supportMapFragment)
        supportMapFragment.getMapAsync(this)
    }

    private fun pickVehicleDetailsFromIntent() {
        vehicleDetailsDTO =
            intent.extras.getParcelable(getString(com.isanaka.mytaxi.R.string.selectedVehicle))
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        vehicleDetailsDTO?.let {
            addMarker(it)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.coordinate, 15.0f))
        }

        map.setOnCameraIdleListener(this)
        map.setOnCameraMoveStartedListener(this)
        hideProgress()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCameraMoveStarted(reason: Int) {

        moveStarted = when (reason) {
            OnCameraMoveStartedListener.REASON_GESTURE -> true
            else -> false
        }
    }

    override fun onCameraIdle() {
        if (moveStarted) {
            showProgress()
            presenter.fetchVehicles(map.projection.visibleRegion.latLngBounds)
            moveStarted = false
        }
    }

    override fun showVehicles(vehicles: List<VehicleDetailsDTO>) {
        map.clear()
        for (vehicle in vehicles) {
            addMarker(vehicle)
        }
        hideProgress()
    }

    private fun addMarker(vehicle: VehicleDetailsDTO?) {
        vehicle?.let {

            val marker = MarkerOptions().position(vehicle.coordinate)
                .title(vehicle.fleetType)
                .snippet(getString(R.string.heading_to, vehicle.direction))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_for_map))
                .anchor(0.5f, 0.5f)
                .rotation(vehicle.heading.toFloat())
                .flat(true)

            map.addMarker(marker)
        }

    }

    override fun showError(message: String) {
        Snackbar.make(main_area, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showInfo() {
        Snackbar.make(main_area, "No Vehicles found", Snackbar.LENGTH_LONG).show()
    }

    override fun onVehicleClicked(vehicleDetailsDTO: VehicleDetailsDTO) {
        // For future use, If we want to perform specific action on click of vehicle on the map.
    }

}
