package com.doniapr.moviecatalogue.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.core.BuildConfig
import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.Movie
import com.doniapr.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    private var isFavorite = false
    private var menu: Menu? = null
    private var contentMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(detail_toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val id = intent.getStringExtra(EXTRA_MOVIE_ID)
        if (id != null) {
            detailMovieViewModel.setMovie(id)
        }
        detailMovieViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> progress_bar_detail_movie.visibility = View.VISIBLE
                    is Resource.Success -> {
                        contentMovie = movie.data!!
                        setContent()
                    }
                    is Resource.Error -> {
                        progress_bar_detail_movie.visibility = View.GONE
                        Toast.makeText(this@DetailMovieActivity, movie.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }

    private fun setContent() {
        progress_bar_detail_movie.visibility = View.GONE
        contentMovie?.let {
            setActionbarTitle(it.title)
            val detailTitle = "${it.title} (${it.releaseDate.slice(0..3)})"
            val runtime = "${it.runtime} menit"

            txt_genre_detail.text = it.genres.toString()
            txt_content_overview.text = it.overview
            txt_content_release_date.text = it.releaseDate
            txt_content_runtime.text = runtime
            title_detail_movie.text = detailTitle
        }

        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie?.posterPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_poster_detail)
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentMovie?.backdropPath)
            .placeholder(R.drawable.poster_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_banner_movie)


    }

    private fun setActionbarTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar?.title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        this.menu = menu
        inflater.inflate(R.menu.menu_detail, menu)

        detailMovieViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Success -> {
                        if (movie.data?.isFavorite!!) {
                            isFavorite = true
                            setFavoriteState(isFavorite)
                        } else {
                            isFavorite = false
                            setFavoriteState(isFavorite)
                        }
                    }
                    else -> {
                        isFavorite = false
                        setFavoriteState(isFavorite)
                    }
                }
            } else {
                isFavorite = false
                setFavoriteState(isFavorite)
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                contentMovie?.let {
                    if (isFavorite) {
                        setFavoriteState(false)
                        detailMovieViewModel.changeFavoriteState(it, false)
                    } else {
                        setFavoriteState(true)
                        detailMovieViewModel.changeFavoriteState(it, true)
                    }
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_yellow)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }
}