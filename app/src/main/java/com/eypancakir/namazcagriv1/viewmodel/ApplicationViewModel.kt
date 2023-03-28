package com.eypancakir.namazcagriv1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.eypancakir.namazcagriv1.LocationLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private  val locationLiveData  = LocationLiveData(application)
    fun getLocationLiveData() = locationLiveData

    fun startLocationUpdates(){
        locationLiveData.startLocationUpdates()
    }


}