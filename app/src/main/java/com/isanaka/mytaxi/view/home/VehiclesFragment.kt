package com.isanaka.mytaxi.view.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.isanaka.mytaxi.R
import com.isanaka.mytaxi.base.BaseFragment
import com.isanaka.mytaxi.core.Config
import com.isanaka.mytaxi.data.model.VehicleDetailsDTO
import com.isanaka.mytaxi.view.common.VehiclesContract
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class VehiclesFragment : BaseFragment(), VehiclesContract.View {

    @Inject
    lateinit var presenter: VehiclesContract.Presenter
    private lateinit var vehiclesAdapter: VehiclesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgress()
        intializeView()
        presenter.attachView(this@VehiclesFragment)
        presenter.fetchVehicles(Config.HAMBURG)
    }

    private fun intializeView() {
        verticalList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vehiclesAdapter = VehiclesAdapter(mutableListOf(), onListingClicked, context!!)
    }

    override fun showVehicles(vehicles: List<VehicleDetailsDTO>) {
        vehiclesAdapter.add(vehicles)
        verticalList.adapter = vehiclesAdapter
        hideProgress()
    }

    override fun onVehicleClicked(vehicleDetailsDTO: VehicleDetailsDTO) {
        (activity as RoutingHelper).onLocateVehicleOnMap(vehicleDetailsDTO)

    }

    override fun onStop() {
        hideProgress()
        super.onStop()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }

    override fun showError(message: String) {
        hideTitles()
        infoAndError.text = message
        infoAndError.visibility = View.VISIBLE
        hideProgress()
    }

    override fun showInfo() {
        hideTitles()
        infoAndError.text = resources.getString(R.string.no_listing_found_for_this_category)
        infoAndError.visibility = View.VISIBLE
        hideProgress()
    }

    private fun hideTitles() {
        title.visibility = View.GONE
        locateVehicleInfo.visibility = View.GONE
    }

    private val onListingClicked: (View, Int) -> Unit = { view, position ->
        showProgress()
        presenter.fetchVehicleSelected(position)
    }

}