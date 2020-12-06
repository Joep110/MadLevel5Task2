package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_game_backlog.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameBacklogFragment : Fragment() {
    private var games: ArrayList<Game> = arrayListOf()

    private lateinit var gameBacklogAdapter: GameBacklogAdapter

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnDeleteAllGames -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        gameBacklogAdapter = GameBacklogAdapter(games)
        rvBacklogScreen.adapter = gameBacklogAdapter
        rvBacklogScreen.layoutManager = GridLayoutManager(context, 1)
        observeAddGameBacklog()
        observeDeleteGameBacklog()
    }

    private fun observeAddGameBacklog() {
        viewModel.games.observe(viewLifecycleOwner) { logs ->
            this@GameBacklogFragment.games.clear()
            this@GameBacklogFragment.games.addAll(logs)
            gameBacklogAdapter.notifyDataSetChanged()
        }
    }

    private fun observeDeleteGameBacklog() {
        viewModel.success.observe(viewLifecycleOwner) { logs ->
            Snackbar.make(View(context), "Success", Snackbar.LENGTH_LONG)
        }
    }
}