package com.eypancakir.namazcagriv1.service

import com.eypancakir.namazcagriv1.model.NamazVakti
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NamazApiService {

    //https://namaz-vakti.vercel.app/api/timesFromCoordinates?lat=39.91987&lng=32.85427&date=2023-03-28&days=30&timezoneOffset=180
    //Base -> https://namaz-vakti.vercel.app/
    //ext -> timesFromCoordinates?lat=39.91987&lng=32.85427&date=2023-03-28&days=30&timezoneOffset=180

    private val BASE_URL = "https://namaz-vakti.vercel.app/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val service = retrofit.create(NamazVaktiService::class.java)

    fun getData() : Single<List<NamazVakti>> {
        return service.getNamazVakti(1.1,1.1,"",1,1)
    }

}