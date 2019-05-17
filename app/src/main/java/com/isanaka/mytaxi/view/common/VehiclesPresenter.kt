package com.isanaka.mytaxi.view.common

import com.google.android.gms.maps.model.LatLngBounds
import com.isanaka.mytaxi.data.model.VehicleDetailsDTO
import com.isanaka.mytaxi.data.model.VehicleDetailsResponse
import com.isanaka.mytaxi.data.network.ApiError
import com.isanaka.mytaxi.data.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable

class VehiclesPresenter(private val repo: AppRepository) : VehiclesContract.Presenter {

    private val compositeDisposable = CompositeDisposable()
    private var view: VehiclesContract.View? = null
    private var vehiclesDTO: MutableList<VehicleDetailsDTO> = mutableListOf()

    override fun attachView(view: VehiclesContract.View) {
        this.view = view
    }

    override fun fetchVehicles(latLngBounds: LatLngBounds) {
        repo.getVehicles(latLngBounds,
            fun(it: VehicleDetailsResponse) {

                if (it.poiList.isNotEmpty()) {
                    val vehicles = it.poiList
                    for (vehicle in vehicles) {
                        vehiclesDTO.add(
                            VehicleDetailsDTO(
                                id = vehicle.id,
                                coordinate = vehicle.coordinate,
                                fleetType = vehicle.fleetType,
                                heading = vehicle.heading,
                                direction = VehicleInfoHelper.getDirection(vehicle.heading)
                            )
                        )
                    }
                    view?.showVehicles(vehiclesDTO)
                } else {
                    view?.showInfo()
                }
            },
            fun(it: ApiError) {
                view?.showError(it.getErrorMessage())
            },
            {
                view?.hideProgress()
            }
        ).also {
            compositeDisposable.add(it)
        }
    }

    override fun fetchVehicleSelected(index: Int) {
        view?.onVehicleClicked(vehiclesDTO[index])
    }

    override fun detachView() {
        this.view = null
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    var success = fun(it: VehicleDetailsResponse) {

        if (it.poiList.isNotEmpty()) {
            val vehicles = it.poiList
            for (vehicle in vehicles) {
                vehiclesDTO.add(
                    VehicleDetailsDTO(
                        id = vehicle.id,
                        coordinate = vehicle.coordinate,
                        fleetType = vehicle.fleetType,
                        heading = vehicle.heading,
                        direction = VehicleInfoHelper.getDirection(vehicle.heading)
                    )
                )
            }
            view?.showVehicles(vehiclesDTO)
        } else {
            view?.showInfo()
        }
    }
}