package com.abc.amphibians.data

import com.abc.amphibians.data.model.Amphibian
import com.abc.amphibians.data.datasource.network.AmphibiansApiService

/**
 * Repository retrieves amphibian data from underlying data source.
 */
interface AmphibiansRepository {
    /** Retrieves list of amphibians from underlying data source */
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Network Implementation of repository that retrieves amphibian data from underlying data source.
 */
class NetworkAmphibiansRepository(private val amphibiansApiService: AmphibiansApiService) :
    AmphibiansRepository {

    /** Retrieves list of amphibians from underlying data source */
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}