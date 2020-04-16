package id.co.tmdb.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import id.co.tmdb.R
import id.co.tmdb.core.Constant
import id.co.tmdb.core.base.BaseFragment
import id.co.tmdb.core.base.BaseListAdapter
import id.co.tmdb.core.extension.json
import id.co.tmdb.databinding.FragmentInfoBinding
import id.co.tmdb.entity.Movie
import id.co.tmdb.ui.vm.InfoVm
import org.koin.androidx.viewmodel.ext.android.viewModel


class InfoFragment : BaseFragment() {
    private lateinit var bind: FragmentInfoBinding
    private lateinit var adapter: BaseListAdapter
    private val vm: InfoVm by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentInfoBinding.inflate(inflater, container, false)
        bind.vm = vm
        vm.setArguments(arguments)
        adapter = BaseListAdapter(R.layout.item_movie)
        bind.tvTrailer.setOnClickListener { watchYoutubeVideo() }
        return bind.root
    }

    fun setMovie(movie: Movie?) {
        vm.movie = movie
        bind.invalidateAll()
    }

    private fun watchYoutubeVideo() {
        val id = vm.getTailerVideoKey()
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }

    companion object {
        fun newInstance(movie: Movie?): InfoFragment {
            val fragment = InfoFragment()
            val args = Bundle()
            args.putBundle(Constant.BUNDLE_DATA, bundleOf(Pair(Constant.MOVIE, movie.json())))
            fragment.arguments = args
            return fragment
        }
    }
}