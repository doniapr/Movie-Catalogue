package com.doniapr.moviecatalogue

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.doniapr.moviecatalogue.movie.MovieFragment
import com.doniapr.moviecatalogue.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_movie -> {
                    navController.navigate(R.id.nav_movie_fragment)
                    main_toolbar.title = resources.getString(R.string.movie)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_tv_show -> {
                    navController.navigate(R.id.nav_tv_show_fragment)
                    main_toolbar.title = resources.getString(R.string.tv_show)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_favorite -> {
                    navController.navigate(R.id.nav_favorite_fragment)
                    main_toolbar.title = resources.getString(R.string.favorite)

                    return@OnNavigationItemSelectedListener true
                }
                else -> throw Throwable("Unknown item Id")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavigation()

        nav_view.setOnNavigationItemSelectedListener(navigationListener)
        if (savedInstanceState == null) {
            nav_view.selectedItemId = R.id.menu_movie
        }
    }

    private fun setUpNavigation() {
        navController = findNavController(R.id.frame_container)
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}