package com.giridharaspk.imagesapplication.data.repository

import com.giridharaspk.imagesapplication.data.api.RetrofitInstance
import com.giridharaspk.imagesapplication.data.model.response.ApiResponse
import retrofit2.Response
import timber.log.Timber

object DataRepository {
    suspend fun getImages(): Response<ApiResponse> {
        Timber.d("getImages REST Api call")
        return RetrofitInstance.api.getLocationDetails()
    }
}