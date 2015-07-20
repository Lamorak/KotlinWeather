package cz.lamorak.kotlinweather.activity

import android.os.Bundle
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import cz.lamorak.kotlinweather.R
import cz.lamorak.kotlinweather.fragment.ForecastFragment
import cz.lamorak.kotlinweather.fragment.TodayFragment
import kotlinx.android.synthetic.activity_main.*
import org.jetbrains.anko.defaultSharedPreferences
import kotlin.properties.Delegates

public class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    val actionBarDrawerToggle: ActionBarDrawerToggle by Delegates.lazy {
        ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawer_layout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        val drawer_toolbar = findViewById(R.id.drawer_toolbar) as Toolbar
        drawer_toolbar.setNavigationOnClickListener {
            drawer_layout.closeDrawers()
        }

        navigation_view.setNavigationItemSelectedListener(this)
        val id = defaultSharedPreferences.getInt("drawer_selected_item", R.id.navigation_item_1)
        val menuItem = navigation_view.getMenu().findItem(id)
        onNavigationItemSelected(menuItem)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem?): Boolean {
        if (menuItem?.isChecked() ?: true) return false

        val fragmentManager = getSupportFragmentManager()
        var fragment = Fragment()
        var tag = ""
        when (menuItem?.getItemId()) {
            R.id.navigation_item_1 -> {
                tag = TodayFragment.tag
                fragment = fragmentManager.findFragmentByTag(tag) ?: TodayFragment()
            }
            R.id.navigation_item_2 -> {
                tag = ForecastFragment.tag
                fragment = fragmentManager.findFragmentByTag(tag) ?: ForecastFragment()
            }
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit()

        menuItem?.setChecked(true)
        var editor = defaultSharedPreferences.edit()
        editor.putInt("drawer_selected_item", menuItem?.getItemId() ?: R.id.navigation_item_1)
        editor.apply()
        drawer_layout.closeDrawers()
        return true
    }
}