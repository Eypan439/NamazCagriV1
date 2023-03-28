package com.eypancakir.namazcagriv1.service

import com.eypancakir.namazcagriv1.model.NamazVakti
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
        @Query("timezoneOffset") timezoneOffset: Int
    ): Single<List<NamazVakti>>
}
