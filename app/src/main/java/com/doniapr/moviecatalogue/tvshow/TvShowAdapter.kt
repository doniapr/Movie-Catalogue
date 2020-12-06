package com.doniapr.moviecatalogue.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doniapr.core.BuildConfig
import com.doniapr.core.domain.model.TvShow
import com.doniapr.moviecatalogue.R
import kotlinx.android.synthetic.main.item_layout.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShow = ArrayList<TvShow>()

    fun setData(tvShows: List<TvShow>?) {
        if (tvShows == null) return
        listTvShow.clear()
        listTvShow.addAll(tvShows)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bindItem(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(tvShow: TvShow) {
            with(itemView) {
                this.setOnClickListener {
                    val intent = Intent(context, DetailTvShowActivity::class.java).apply {
                        putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, tvShow)
                    }
                    context?.startActivity(intent)
                }
                val textTitle = "${tvShow.name} (${tvShow.firstAirDate.slice(0..3)})"
                txt_title.text = textTitle
                Glide.with(this.context).load(BuildConfig.BASE_URL_IMAGE + tvShow.posterPath)
                    .placeholder(R.drawable.poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img_poster)
            }
        }
    }
}