package com.doniapr.moviecatalogue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.doniapr.core.data.Resource
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra(EXTRA_MOVIE_ID)
        if (id != null) {
            detailMovieViewModel.setMovie(id)
        }
        detailMovieViewModel.movie.observe(this, Observer { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> txt_detail_movie_title.text = "Loading"
                    is Resource.Success -> txt_detail_movie_title.text = movie.data?.title
                    is Resource.Error -> txt_detail_movie_title.text = "Error"
                }
            }
        })
    }
}