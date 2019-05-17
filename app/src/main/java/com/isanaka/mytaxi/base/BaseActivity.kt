package com.isanaka.mytaxi.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.isanaka.mytaxi.R
import com.isanaka.mytaxi.core.replaceFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : DaggerAppCompatActivity(), BaseInteractions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    fun replaceFragment(fragment: Fragment) {
        replaceFragment(fragment, R.id.mainContainer)
    }
}