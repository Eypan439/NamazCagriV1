package com.eypancakir.namazcagriv1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.eypancakir.namazcagriv1.LocationLiveData
import com.eypancakir.namazcagriv1.service.NamazApiService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {

    private val namazApiService = NamazApiService()
    private val disposable = CompositeDisposable()

    private  val locationLiveData  = LocationLiveData(application)
    fun getLocationLiveData() = locationLiveData

    fun startLocationUpdates(){
        locationLiveData.startLocationUpdates()
        getDataFromApi()
    }

    private fun getDataFromApi() {

        disposable.add(
            namazApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.)
        )

    }

}