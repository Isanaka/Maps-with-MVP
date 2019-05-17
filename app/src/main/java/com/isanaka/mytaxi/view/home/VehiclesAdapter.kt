package com.isanaka.mytaxi.view.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.isanaka.mytaxi.R
import com.isanaka.mytaxi.core.onClick
import com.isanaka.mytaxi.data.model.VehicleDetailsDTO
import kotlinx.android.synthetic.main.vehicle_list_item.view.*

class VehiclesAdapter(
    private var items: MutableList<VehicleDetailsDTO>,
    private var onclick: ((View, Int) -> Unit),
    private val context: Context
) :
    RecyclerView.Adapter<VehiclesAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemHolder =
        ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.vehicle_list_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
        holder.onClick(onclick)
    }

    inner class ItemHolder(
        val view: View

    ) : RecyclerView.ViewHolder(view) {

        private val vehicleIcon: ImageView = view.icon
        private val vehicleId: TextView = view.vehicleId
        private val vehicleType: TextView = view.vehicleType
        private val headingDirection: TextView = view.headingDirection

        fun bind(item: VehicleDetailsDTO) = with(item) {
            vehicleId.text = this.id.toString()
            when (this.fleetType) {
                context.getString(R.string.pooling) -> vehicleIcon.setImageResource(R.drawable.car_pool)
                else -> vehicleIcon.setImageResource(R.drawable.car)
            }
            vehicleType.text = this.fleetType
            headingDirection.text = context.getString(R.string.heading_to, this.direction)
        }

    }

    fun add(list: List<VehicleDetailsDTO>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}