package com.luc.base.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions


open class BaseFragment : Fragment() {

    lateinit var mActivity: BaseActivity
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = activity as BaseActivity
        super.onCreate(savedInstanceState)
    }

    protected open fun showProgress() {
        mActivity.showProgress()
    }

    protected open fun hideProgress() {
        mActivity.hideProgress()
    }

    protected open fun getNavOptions(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(android.R.anim.fade_in)
            .setExitAnim(android.R.anim.fade_out)
            .setPopEnterAnim(android.R.anim.fade_in)
            .setPopExitAnim(android.R.anim.fade_out)
            .build()
    }
}