package com.doniapr.moviecatalogue.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doniapr.moviecatalogue.R

class DetailTvShowActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW_ID = "tv_show_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
    }
}