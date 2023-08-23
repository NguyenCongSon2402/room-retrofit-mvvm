package com.example.retrofitroom.retrofit

import com.example.retrofitroom.constant.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Lớp GetRetrofit để khởi tạo một đối tượng Retrofit
class GetRetrofit {
    // Phương thức này trả về một đối tượng Retrofit đã được cấu hình
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Đặt URL cơ sở cho các cuộc gọi mạng
            .addConverterFactory(GsonConverterFactory.create()) // Sử dụng Gson để chuyển đổi JSON thành các đối tượng Kotlin
            .build() // Xây dựng đối tượng Retrofit
    }
}