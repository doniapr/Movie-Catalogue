package com.doniapr.moviecatalogue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_tv_show -> {
                    fragment = TvShowFragment()
                    changeFragment(fragment)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_favorite -> {
                    fragment = FavoriteFragment()
                    changeFragment(fragment)

                    return@OnNavigationItemSelectedListener true
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