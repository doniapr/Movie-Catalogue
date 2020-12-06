package com.doniapr.moviecatalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doniapr.core.domain.model.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private var listReviews = ArrayList<Review>()

    fun setData(reviews: List<Review>?) {
        if (reviews == null) return
        listReviews.clear()
        listReviews.addAll(reviews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindItem(listReviews[position])
    }

    override fun getItemCount(): Int = listReviews.size

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(review: Review) {
            with(itemView) {
                txt_review_author.text = review.author
                txt_review_content.text = review.content
            }
        }

    }
}