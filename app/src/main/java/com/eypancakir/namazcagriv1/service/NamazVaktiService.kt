package com.eypancakir.namazcagriv1.service

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NamazVaktiService {

    @GET("timesFromCoordinates")
    fun getNamazVakti(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("date") date: String,
        @Query("days") days: Int,
        @Query("timezoneOffset") timezoneOffset: Int): Single<NamazVaktiResponse>

}

data class NamazVaktiResponse(
    val place: NamazVaktiPlace,
    val times: HashMap<String, List<String>>
)

data class NamazVaktiPlace(
    val countryCode: String,
    val country: String,
    val region: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)