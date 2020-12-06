package com.doniapr.moviecatalogue.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.doniapr.moviecatalogue.favorite.R
import com.doniapr.moviecatalogue.tvshow.TvShowAdapter
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment() {

    private val tvShowFavoriteViewModel: TvShowFavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowAdapter()

        tvShowFavoriteViewModel.tvShows.observe(this, {
            if (it != null && it.isNotEmpty()) {
                tvShowAdapter.setData(it)
                txt_error_tv_show_favorite.visibility = View.GONE
            } else {
                txt_error_tv_show_favorite.visibility = View.VISIBLE
            }
        })

        with(rv_tv_show_favorite) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}