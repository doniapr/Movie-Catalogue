package com.doniapr.moviecatalogue

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doniapr.core.domain.model.Movie
import com.doniapr.moviecatalogue.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieList = ArrayList<Movie>()

    fun setData(newMovieList: List<Movie>?) {
        if (newMovieList == null) return
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: Movie) {
            with(itemView) {
                txt_movie_title.text = movie.title

                setOnClickListener {
                    val intent = Intent(context, DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE_ID, movie.id.toString())
                    context.startActivity(intent)
                }
            }
        }

    }
}