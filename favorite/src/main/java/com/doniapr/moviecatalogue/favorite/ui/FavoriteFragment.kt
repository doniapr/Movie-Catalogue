package com.doniapr.moviecatalogue.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doniapr.moviecatalogue.favorite.R
import com.doniapr.moviecatalogue.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        setUpPager()
    }

    private fun setUpPager() {
        val favoritePagerAdapter =
            FavoritePagerAdapter(
                childFragmentManager
            )
        vp_favorite.adapter = favoritePagerAdapter
        tab_favorite.setupWithViewPager(vp_favorite)
    }

}