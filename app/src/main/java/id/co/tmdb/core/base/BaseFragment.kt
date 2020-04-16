package id.co.tmdb.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {
    lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = activity as BaseActivity
        super.onCreate(savedInstanceState)
    }
}