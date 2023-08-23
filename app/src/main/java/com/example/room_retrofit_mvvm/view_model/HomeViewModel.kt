package com.example.retrofitroom.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.database.model.Character
import com.example.retrofitroom.database.model.CharacterDatabase

import com.example.retrofitroom.database.model.DataResult
import com.example.retrofitroom.network.ApiRepo
import com.example.retrofitroom.network.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val apiRepo: ApiRepo, private val db: CharacterDatabase) : ViewModel(){

    private val _stateListCharacter = MutableLiveData<Resource<DataResult>>()
    val stateListCharacter: LiveData<Resource<DataResult>> = _stateListCharacter

    private var _listState = MutableLiveData<List<Character>>()
    val listState: LiveData<List<Character>> = _listState

    fun getAllCharacter(page: Int){
        viewModelScope.launch {
            _stateListCharacter.value = Resource.loading()
            apiRepo.getAllCharacter(page).collect(){
                if(it.status == Resource.Status.SUCCESS){
                    if(it.data != null){
                        _stateListCharacter.value = Resource.success(it.data) // data results
                        addItems(it.data.results)
                    } else{
                        _stateListCharacter.value = Resource.failed(msg = "Data null")
                    }
                } else{
                    _stateListCharacter.value = Resource.failed(msg = "Call failed")
                }
            }
        }
    }

    fun getAllData(){
       viewModelScope.launch{
           _listState.value = db.characterDao().getAllData()
       }
    }

    fun addItems(characters: List<Character>){
        viewModelScope.launch {
            db.characterDao().addItems(characters)
            getAllData()
        }
    }
}