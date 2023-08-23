package com.example.retrofitroom.database.model

import androidx.room.*

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItems(character: List<Character>)

    @Delete
    suspend fun deleteItem(character: Character)

    @Update
    suspend fun updateItem(character: Character)

    @Query("SELECT * FROM table_character_1")
    suspend fun getAllData(): List<String>

}