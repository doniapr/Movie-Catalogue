package com.doniapr.moviecatalogue.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.doniapr.moviecatalogue.favorite.R
import com.doniapr.moviecatalogue.movie.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {

    private val movieFavoriteViewModel: MovieFavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()

        movieFavoriteViewModel.movies.observe(this, {
            if (it != null && it.isNotEmpty()) {
                movieAdapter.setData(it)
                txt_error_movie_favorite.visibility = View.GONE
            } else {
                txt_error_movie_favorite.visibility = View.VISIBLE
            }
        })

        with(rv_movie_favorite) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}