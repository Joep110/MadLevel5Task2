package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable ORDER BY releaseDate ASC")
    fun getAllGames(): LiveData<List<Game>>

    @Query("DELETE FROM gameTable WHERE id = :id")
    suspend fun deleteGame(id: Int)

    @Query("DELETE FROM gameTable")
    suspend fun deleteGames()
}