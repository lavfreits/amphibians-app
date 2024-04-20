package com.example.amphitians.network
import com.example.amphitians.model.Amphibian
import retrofit2.http.GET

interface AmphibiansService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}


