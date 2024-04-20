package com.example.amphitians.data

import com.example.amphitians.network.AmphibiansService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val repository: Repository
}

class DefaultAppContainer :  AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit : Retrofit =
        Retrofit.Builder()
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    private val retrofitService : AmphibiansService by lazy {
        retrofit.create(AmphibiansService::class.java)
    }

    override val repository: Repository by lazy {
        NetworkRepository(retrofitService)
    }
}