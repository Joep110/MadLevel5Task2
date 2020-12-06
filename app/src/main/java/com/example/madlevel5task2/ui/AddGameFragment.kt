package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.item_card_game_backlog.*
import java.time.LocalDate
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabSaveGame.setOnClickListener {
            saveGame()
        }
    }

    private fun saveGame() {
        val date = Date(txtAddYear.text.toString().toInt(), txtAddMonth.text.toString().toInt(), txtAddDay.text.toString().toInt())
        viewModel.insertGame(txtAddTitle.text.toString(), txtAddPlatform.text.toString(), date)


    }
}