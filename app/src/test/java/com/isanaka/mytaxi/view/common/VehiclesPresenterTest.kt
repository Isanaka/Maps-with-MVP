package com.isanaka.mytaxi.view.common

import com.isanaka.mytaxi.data.repository.AppRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehiclesPresenterTest {

    @InjectMocks
    lateinit var presenter: VehiclesContract.Presenter
    @Mock
    lateinit var mockRepo: AppRepository
    @Mock
    lateinit var mockView: VehiclesContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun attachView() {
        presenter.attachView(mockView)
    }

    @Test
    fun fetchVehicles() {
    }

    @Test
    fun onVehicleSelected() {
    }

    @Test
    fun detachView() {
    }
}