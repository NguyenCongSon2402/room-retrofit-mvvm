package com.example.retrofitroom.network

import com.example.retrofitroom.database.model.DataResult
import com.example.retrofitroom.network.ApiService
import retrofit2.Response
// Lớp ApiDatasource để đóng gói các cuộc gọi mạng
class ApiDatasource(private val apiService: ApiService) {
    // Phương thức này thực hiện cuộc gọi mạng để lấy danh sách kết quả dữ liệu
    suspend fun getAllCharacter(page: Int): Response<DataResult> {
        return apiService.getListCharacter(page)
    }
}