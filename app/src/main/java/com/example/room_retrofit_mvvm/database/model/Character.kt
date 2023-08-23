package com.example.retrofitroom.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_character_1")
data class Character(
    @PrimaryKey
    val name: String,
    val image:String
)