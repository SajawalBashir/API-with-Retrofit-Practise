package com.example.retrofitprac

import com.example.retrofitprac.dataClass.CustomModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//interface MovieApi {
//    @GET("popular?")
//    fun getPopularMovies(@Query("api_key") api_key : String) : Call<Movies
//}

//interface dataApi {
//
//    @GET("todos")
//    fun getData() : Call<CustomModelElement>
//
//    companion object {
//        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//    }
//}

//interface dataApi{
//
//    @GET("todos/")
//    fun getData() : Call<List<CustomModelElement>>
//
//}

interface dataApi{
    @GET("carts")
    fun getData() : Call<CustomModel>
}