package com.giridharaspk.imagesapplication.data.api

import com.giridharaspk.imagesapplication.data.model.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getLocationDetails(): Response<ApiResponse>

}