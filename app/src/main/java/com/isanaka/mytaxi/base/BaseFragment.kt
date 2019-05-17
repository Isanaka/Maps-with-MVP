package com.isanaka.mytaxi.base

import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment(), BaseInteractions {
    override fun showProgress() {
        (activity as BaseInteractions).showProgress()
    }

    override fun hideProgress() {
        (activity as BaseInteractions).hideProgress()
    }
}