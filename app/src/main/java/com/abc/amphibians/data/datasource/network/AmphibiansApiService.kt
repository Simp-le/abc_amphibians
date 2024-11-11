package com.abc.amphibians.data.datasource.network

import com.abc.amphibians.data.model.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}