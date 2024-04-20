package com.example.amphitians.data

import com.example.amphitians.model.Amphibian
import com.example.amphitians.network.AmphibiansService

interface Repository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkRepository(
    private val amphibiansApiService: AmphibiansService
) : Repository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}