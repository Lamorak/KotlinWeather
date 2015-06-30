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
import cz.lamorak.kotlinweather.fragment.TodayFragment
import kotlinx.android.synthetic.activity_main.*
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
        val menuItem = navigation_view.getMenu().findItem(R.id.navigation_item_1)
        onNavigationItemSelected(menuItem)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem?): Boolean {
        if (menuItem?.isChecked() ?: true) return false

        val fragmentTransaction = getSupportFragmentManager().beginTransaction()
        when (menuItem?.getItemId()) {
            R.id.navigation_item_1 -> {
                fragmentTransaction.replace(R.id.fragment_container, TodayFragment.newInstance()).commit()
            }
        }

        menuItem?.setChecked(true)
        drawer_layout.closeDrawers()
        return true
    }
}