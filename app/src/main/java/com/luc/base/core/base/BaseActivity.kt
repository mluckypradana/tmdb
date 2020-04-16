package com.luc.base.core.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.luc.base.R
import com.luc.base.core.Constant
import com.luc.base.core.control.ActivityController
import com.luc.base.core.helper.Common
import com.luc.base.core.listener.OnBackPressedListener
import com.luc.base.event.AutoLogoutEvent
import com.luc.base.ui.activity.MainActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {
    private var onBackPressedListener: OnBackPressedListener? = null
    private lateinit var grantResult: (Int, PermissionResult) -> Unit

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onAutoLogout(event: AutoLogoutEvent) {
        ActivityController.navigateWithClearTop(this, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    open fun showProgress() = Common.showProgressDialog(this, R.string.message_loading)

    open fun hideProgress() = Common.dismissProgressDialog()


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constant.REQUEST_LOCATION -> {
                var permissionResult: PermissionResult = PermissionResult.GRANTED
                run breaker@{
                    permissions.forEach {
                        permissionResult = convertToPermissionResult(it)
                        if (permissionResult != PermissionResult.GRANTED) {
                            return@breaker
                        }
                    }
                }
                grantResult(requestCode, permissionResult)

            }
        }
    }

    private fun Activity.convertToPermissionResult(permission: String): PermissionResult {
        return if ((ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)) {
            PermissionResult.GRANTED
        } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            PermissionResult.DO_NOT_ASK_AGAIN
        } else {
            PermissionResult.NOT_GRANTED
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (onBackPressedListener != null)
            onBackPressedListener?.onBackPressed()
    }

}

enum class PermissionResult {
    GRANTED,
    NOT_GRANTED,
    DO_NOT_ASK_AGAIN
}