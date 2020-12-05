package com.doniapr.moviecatalogue.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.doniapr.core.data.Resource
import com.doniapr.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowAdapter = TvShowAdapter()

        tvShowViewModel.tvShows.observe(this, Observer { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> progress_bar_tv_show.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar_tv_show.visibility = View.GONE
                        rv_tv_show.visibility = View.VISIBLE
                        txt_error_tv_show.visibility = View.GONE
                        tvShowAdapter.setData(tvShow.data)
                    }
                    is Resource.Error -> {
                        progress_bar_tv_show.visibility = View.GONE
                        rv_tv_show.visibility = View.GONE
                        txt_error_tv_show.visibility = View.VISIBLE
                        txt_error_tv_show.text = tvShow.message ?: "Oops.. something went wrong"
                    }
                }
            }
        })

        with(rv_tv_show) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

    }
}