package com.doniapr.moviecatalogue.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.core.BuildConfig
import com.doniapr.core.domain.model.Movie
import com.doniapr.moviecatalogue.R
import com.doniapr.moviecatalogue.utils.ParamDetail.Companion.EXTRA_MOVIE_ID
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieList = ArrayList<Movie>()

    fun setData(newMovieList: List<Movie>?) {
        if (newMovieList == null) return
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: Movie) {
            with(itemView) {
                setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, movie)
                    context.startActivity(intent)
                }
                val textTitle = "${movie.title} (${movie.releaseDate.slice(0..3)})"
                txt_title.text = textTitle
                Glide.with(this.context).load(BuildConfig.BASE_URL_IMAGE + movie.posterPath)
                    .placeholder(R.drawable.poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img_poster)
            }
        }

    }
}