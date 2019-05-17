package com.isanaka.mytaxi.base

interface BasePresenter<T> {
    fun attachView(view: T)
    fun detachView()
}
