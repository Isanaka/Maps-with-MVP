package com.isanaka.mytaxi.view.common

class VehicleInfoHelper {

    companion object {
        fun getDirection(angle: Double): String {
            val directions = arrayOf(
                "North",
                "North-West",
                "West",
                "South-West",
                "South",
                "South-East",
                "East",
                "North-East"
            )
            return directions[Math.floor((angle / 45)).toInt()]
        }
    }

}