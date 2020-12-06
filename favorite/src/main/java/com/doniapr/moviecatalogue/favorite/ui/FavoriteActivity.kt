package com.doniapr.moviecatalogue.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.doniapr.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
import com.doniapr.moviecatalogue.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)
        setUpPager()

    }

    private fun setUpPager() {
        val favoritePagerAdapter =
            FavoritePagerAdapter(
                supportFragmentManager
            )
        binding.vpFavorite.adapter = favoritePagerAdapter
        tab_favorite.setupWithViewPager(binding.vpFavorite)
        supportActionBar?.elevation = 0f
    }
}