package id.co.tmdb.core.control

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import org.koin.core.KoinComponent

object ActivityController : KoinComponent {
    fun navigateTo(
        from: Activity?,
        to: Class<out Any>,
        finish: Boolean = false,
        bundle: Bundle? = null
    ) {
        navigateToRight(from, to, finish, bundle)
    }

    private fun navigateToRight(
        from: Activity?,
        to: Class<out Any>,
        finish: Boolean = false,
        bundle: Bundle? = null
    ) {
        val intent = Intent(from, to)
        if (bundle != null)
            intent.putExtras(bundle)

        from?.startActivity(intent)
        if (finish) from?.finish()
    }
}