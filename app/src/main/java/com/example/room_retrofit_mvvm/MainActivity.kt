package com.example.room_retrofit_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitroom.database.model.Character
import com.example.retrofitroom.database.model.CharacterDatabase
import com.example.retrofitroom.network.ApiDatasource
import com.example.retrofitroom.network.ApiRepo
import com.example.retrofitroom.network.ApiService
import com.example.retrofitroom.network.Resource
import com.example.retrofitroom.retrofit.GetRetrofit
import com.example.retrofitroom.view_model.HomeViewModel
import com.example.retrofitroom.view_model.HomeViewModelFactory
import com.example.room_retrofit_mvvm.adapter.CharacterAdapter
import com.example.room_retrofit_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: CharacterAdapter
    private var mListCharacter: ArrayList<Character> = arrayListOf()

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mApiSerVice: ApiService
    private lateinit var mApiRepo: ApiRepo
    private lateinit var mApiDatasource: ApiDatasource
    private lateinit var mDatabase: CharacterDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mApiSerVice = GetRetrofit().getInstance().create(ApiService::class.java)// khởi tạo đối tượng retrofit để thực hiện cuộc gọi mạng
        mApiDatasource = ApiDatasource(mApiSerVice)// Khởi tạo một đối tượng ApiDatasource để thực hiện các cuộc gọi mạng sử dụng đối tượng apiService
        mApiRepo = ApiRepo(mApiDatasource)
        mDatabase = CharacterDatabase.getInstance(this)
        val factory = HomeViewModelFactory(mApiRepo, mDatabase)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        initView()
        initObserve()
        homeViewModel.getAllCharacter(2)
        homeViewModel.getAllData()
    }

    private fun initObserve() {
        homeViewModel.stateListCharacter.observe(this) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.e("log", "...Loading...")
                }

                Resource.Status.FAILED -> {
                    Toast.makeText(this@MainActivity, "Error:${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.SUCCESS -> {
                    val listCharacter = it.data!!.results // ArrayList<Character>
                    mListCharacter.addAll(listCharacter)
                    mAdapter.notifyDataSetChanged()
                }
            }
        }
        homeViewModel.listState.observe(this) {
            binding.txtTitle.text = "Size: ${it.size}"
        }
    }

    private fun initView() {
        mAdapter = CharacterAdapter(this, mListCharacter)
        var linearLayout = LinearLayoutManager(this)
        binding.rcv.adapter = mAdapter
        binding.rcv.layoutManager = linearLayout
    }
}