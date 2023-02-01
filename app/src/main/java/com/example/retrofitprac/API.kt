package com.example.retrofitprac

import retrofit2.Call
import retrofit2.http.GET

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

interface dataApi{

    @GET("todos/")
    fun getData() : Call<List<CustomModelElement>>

}