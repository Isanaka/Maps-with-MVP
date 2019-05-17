package com.isanaka.mytaxi.view.common

import junit.framework.Assert.assertEquals
import org.junit.Test

class VehicleInfoHelperTest {

    @Test
    fun `getDirection for boundary conditions for North `() {
        assertEquals("North", VehicleInfoHelper.getDirection(12.00))
        assertEquals("North", VehicleInfoHelper.getDirection(0.00))
        assertEquals("North", VehicleInfoHelper.getDirection(44.8000))
    }

    @Test
    fun `getDirection for boundary conditions for North-West `() {
        assertEquals("North-West", VehicleInfoHelper.getDirection(45.00))
        assertEquals("North-West", VehicleInfoHelper.getDirection(45.50))
        assertEquals("North-West", VehicleInfoHelper.getDirection(46.00))
        assertEquals("North-West", VehicleInfoHelper.getDirection(50.00))
    }

    @Test
    fun `getDirection for boundary conditions for North-East `() {
        assertEquals("North-East", VehicleInfoHelper.getDirection(316.00))
        assertEquals("North-East", VehicleInfoHelper.getDirection(350.50))
        assertEquals("North-East", VehicleInfoHelper.getDirection(359.8777))
    }
}