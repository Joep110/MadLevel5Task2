package com.example.madlevel5task2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.*
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games = gameRepository.getAllGames()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(title: String, platform: String, releaseDate: Date) {
        val newGame = Game(
            title = title,
            platform = platform,
            releaseDate = releaseDate
        )
        if(isGameValid(newGame)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(newGame)
                }
                success.value = true
            }
        }
    }

    fun deleteGames() {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGames()
            }
        }
    }

    fun deleteGame(game: Game) {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGame(game)
            }
        }
    }

    private fun isGameValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            game.platform.isBlank() -> {
                error.value = "Platform must not be empty"
                false
            }
            else -> true
        }
    }

}