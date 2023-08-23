package com.example.retrofitroom.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroom.database.model.CharacterDatabase
import com.example.retrofitroom.network.ApiRepo

class HomeViewModelFactory(private val apiRepo: ApiRepo, private val db:CharacterDatabase):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(apiRepo, db) as T
    }
}