package cz.lamorak.kotlinweather.activity

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import cz.lamorak.kotlinweather.R
import kotlinx.android.synthetic.activity_main.*
import kotlin.properties.Delegates

public class MainActivity : AppCompatActivity() {
    val actionBarDrawerToggle: ActionBarDrawerToggle by Delegates.lazy {
        ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawer_layout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }
}