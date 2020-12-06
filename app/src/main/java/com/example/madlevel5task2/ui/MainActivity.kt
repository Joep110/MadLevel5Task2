package com.example.madlevel5task2.ui

import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: GameViewModel by viewModels()

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.SecondFragment)) {
                menu.findItem(R.id.btnDeleteAllGames).isVisible = false
                menu.findItem(R.id.btnDeleteAllGames).isVisible = true
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
                supportActionBar?.title = "Add Game"
                fabAddGame.hide()
                fabSaveGame.show()
                fabSaveGame.setOnClickListener {
                    saveGame()
                }
            } else {
                menu.findItem(R.id.btnDeleteAllGames).isVisible = true
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
                supportActionBar?.title = "Game Backlog"
                fabAddGame.show()
                fabSaveGame.hide()
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveGame() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, txtAddYear.text.toString().toInt())
        cal.set(Calendar.MONTH, txtAddMonth.text.toString().toInt())
        cal.set(Calendar.DAY_OF_MONTH, txtAddDay.text.toString().toInt())

        viewModel.insertGame(txtAddTitle.text.toString(), txtAddPlatform.text.toString(), Date.from(cal.toInstant()))
        navController.navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.btnDeleteAllGames -> {
                viewModel.deleteGames()
                true
            }
            16908332 -> {
                navController.navigate(
                    R.id.action_SecondFragment_to_FirstFragment
                )
                true
            }
            else -> false
        }
    }
}