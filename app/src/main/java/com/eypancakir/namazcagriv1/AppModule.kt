package com.eypancakir.namazcagriv1

import com.eypancakir.namazcagriv1.viewmodel.ApplicationViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ApplicationViewModel(androidApplication()) }
}