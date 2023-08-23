package com.example.retrofitroom.database.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao() : CharacterDao

    companion object{
        const val CHARACTER_DATABASE_NAME = "CHARACTER_DATABASE_NAME"
        private var INSTANCE : CharacterDatabase? = null

        val migration = object : Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE ${"table_call_api"} (${"name"} TEXT, ${"image"} TEXT)")
            }
        }

        fun getInstance(context: Context): CharacterDatabase{
            val instance = INSTANCE
            if(instance != null){
                return instance
            }
            synchronized(this){
                val temp = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    CHARACTER_DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(migration)
                    .build()
                INSTANCE = temp
                return temp
            }
        }
    }
}