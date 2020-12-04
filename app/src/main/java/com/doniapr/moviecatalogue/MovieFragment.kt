package com.doniapr.moviecatalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.doniapr.core.data.Resource
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()

        mainViewModel.movies.observe(this, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> progress_bar_movie.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar_movie.visibility = View.GONE
                        rv_movie.visibility = View.VISIBLE
                        txt_error_movie.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        progress_bar_movie.visibility = View.GONE
                        rv_movie.visibility = View.GONE
                        txt_error_movie.visibility = View.VISIBLE
                        txt_error_movie.text = movie.message ?: "Oops.. something went wrong"
                    }
                }
            }
        })

        with(rv_movie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}