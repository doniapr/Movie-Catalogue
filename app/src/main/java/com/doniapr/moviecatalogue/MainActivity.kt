package com.doniapr.moviecatalogue

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.doniapr.moviecatalogue.movie.MovieFragment
import com.doniapr.moviecatalogue.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.menu_movie -> {
                    fragment = MovieFragment()
                    changeFragment(fragment)
                    main_toolbar.title = resources.getString(R.string.movie)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_tv_show -> {
                    fragment = TvShowFragment()
                    changeFragment(fragment)
                    main_toolbar.title = resources.getString(R.string.tv_show)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_favorite -> {
                    val uri = Uri.parse("moviecatalogue://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    return@OnNavigationItemSelectedListener false
                }
                else -> throw Throwable("Unknown item Id")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(navigationListener)
        if (savedInstanceState == null) {
            nav_view.selectedItemId = R.id.menu_movie
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}