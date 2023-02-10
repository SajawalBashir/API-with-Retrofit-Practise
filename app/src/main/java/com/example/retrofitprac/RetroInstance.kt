package com.example.retrofitprac

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetroInstance {
//
////    val api: MovieApi by lazy {
////        Retrofit.Builder()
////            .baseUrl("https://api.themoviedb.org/3/movie/")
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////            .create(MovieApi::class.java)
////    }
//
//
//    val api: dataApi by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(dataApi::class.java)
//    }
//}

class RetroInstance private constructor() {
    private val myApi: dataApi

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi = retrofit.create(dataApi::class.java)
    }

    fun getMyApi(): dataApi {
        return myApi
    }

    companion object {
        @get:Synchronized
        var instance: RetroInstance? = null
            get() {
                if (field == null) {
                    field = RetroInstance()
                }
                return field
            }
            private set
    }
}