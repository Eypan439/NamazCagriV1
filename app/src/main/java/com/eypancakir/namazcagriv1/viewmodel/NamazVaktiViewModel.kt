package com.eypancakir.namazcagriv1.viewmodel

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eypancakir.namazcagriv1.service.NamazVaktiResponse
import com.eypancakir.namazcagriv1.service.NamazVaktiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import kotlin.coroutines.coroutineContext
import kotlin.properties.Delegates

@RequiresApi(Build.VERSION_CODES.O)
class NamazVaktiViewModel(var lat: Double = 41.1, var lng: Double = 37.2) : ViewModel() {


    private val namazVaktiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://namaz-vakti.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(NamazVaktiService::class.java)
    }

    private val disposable = CompositeDisposable()

    private val _namazVakti = MutableLiveData<NamazVaktiResponse>()
    val namazVakti: LiveData<NamazVaktiResponse> = _namazVakti

    fun refreshData(){

        val date = LocalDate.now().toString()
        disposable.add(namazVaktiService.getNamazVakti(lat = lat, lng = lng, date, 30, 180)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                _namazVakti.postValue(response)
            }, { throwable ->
                // handle error
            })
        )

    }

    init {
        val date = LocalDate.now().toString()
        disposable.add(namazVaktiService.getNamazVakti(lat = lat, lng = lng, date, 30, 180)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                _namazVakti.postValue(response)
            }, { throwable ->
                // handle error
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}