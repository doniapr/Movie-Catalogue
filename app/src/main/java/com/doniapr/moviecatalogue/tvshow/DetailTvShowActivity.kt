package com.doniapr.moviecatalogue.tvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.core.BuildConfig
import com.doniapr.core.data.Resource
import com.doniapr.core.domain.model.TvShow
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.ReviewAdapter
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.txt_no_review_found
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW_ID = "tv_show_id"
    }

    private val detailTvShowViewModel: DetailTvShowViewModel by viewModel()

    private var isFavorite = false
    private var menu: Menu? = null
    private var contentTvShow: TvShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        setSupportActionBar(detail_toolbar_tv_show)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val id = intent.getStringExtra(EXTRA_TV_SHOW_ID)
        val reviewAdapter = ReviewAdapter()
        if (id != null) {
            detailTvShowViewModel.setTvShow(id)
        }
        detailTvShowViewModel.tvShow.observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> progress_bar_detail_tv_show.visibility = View.VISIBLE
                    is Resource.Success -> {
                        contentTvShow = tvShow.data!!
                        setContent()
                    }
                    is Resource.Error -> {
                        progress_bar_detail_tv_show.visibility = View.GONE
                        Toast.makeText(
                                this@DetailTvShowActivity,
                                tvShow.message,
                                Toast.LENGTH_SHORT
                        )
                                .show()
                    }
                }
            }
        })

        detailTvShowViewModel.reviews.observe(this, { reviews ->
            if (reviews != null) {
                when (reviews) {
                    is Resource.Success -> {
                        progress_bar_review_tv_show.visibility = View.GONE

                        if (reviews.data != null && reviews.data!!.isNotEmpty()) {
                            txt_no_review_found.visibility = View.GONE
                            reviewAdapter.setData(reviews.data)
                        } else {
                            txt_no_review_found.visibility = View.VISIBLE
                        }
                    }
                    is Resource.Loading -> progress_bar_review_tv_show.visibility = View.VISIBLE
                    is Resource.Error -> {
                        progress_bar_review_tv_show.visibility = View.GONE
                        txt_no_review_found.visibility = View.VISIBLE
                    }
                }
            }
        })

        with(rv_review_tv_show) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reviewAdapter
        }
    }

    private fun setContent() {
        progress_bar_detail_tv_show.visibility = View.GONE
        contentTvShow?.let {
            setActionbarTitle(it.name)
            val detailTitle = "${it.name} (${it.firstAirDate.slice(0..3)})"
            val runtime = "${it.episodeRunTime} menit"

            txt_genre_detail_tv_show.text = it.genres.toString()
            txt_content_overview_tv_show.text = it.overview
            txt_content_release_date_tv_show.text = it.firstAirDate
            txt_content_runtime_tv_show.text = runtime
            title_detail_tv_show.text = detailTitle
        }

        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow?.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img_poster_detail_tv_show)
        Glide.with(this).load(BuildConfig.BASE_URL_IMAGE + contentTvShow?.backdropPath)
                .placeholder(R.drawable.poster_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img_banner_tv_show)


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

        detailTvShowViewModel.tvShow.observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Success -> {
                        if (tvShow.data?.isFavorite!!) {
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
                contentTvShow?.let {
                    if (isFavorite) {
                        setFavoriteState(false)
                        detailTvShowViewModel.changeFavoriteState(it, false)
                    } else {
                        setFavoriteState(true)
                        detailTvShowViewModel.changeFavoriteState(it, true)
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