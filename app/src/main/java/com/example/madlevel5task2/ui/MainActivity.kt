package com.example.madlevel5task2.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)

        fabAddGame.setOnClickListener {
            navController.navigate(
                R.id.action_FirstFragment_to_SecondFragment
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.SecondFragment)) {
                menu.findItem(R.id.btnDeleteAllGames).isVisible = false
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
                supportActionBar?.title = "Add Game"
                fabAddGame.hide()
            } else {
                menu.findItem(R.id.btnDeleteAllGames).isVisible = true
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
                supportActionBar?.title = "Game Backlog"
                fabAddGame.show()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        println(item.itemId)
        return when (item.itemId) {
            R.id.btnDeleteAllGames -> {
                true
            }
            16908332 -> {
                navController.navigate(
                    R.id.action_SecondFragment_to_FirstFragment
                )
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}