package com.giridharaspk.imagesapplication.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.giridharaspk.imagesapplication.data.api.ApiResult
import com.giridharaspk.imagesapplication.data.model.response.ApiResponse
import com.giridharaspk.imagesapplication.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class MainViewModel(app: Application, private val dataRepository: DataRepository) :
    AndroidViewModel(app) {

    val imagesList =
        MutableLiveData<ApiResult<ApiResponse>>()

    fun getImages() = viewModelScope.launch(Dispatchers.IO) {
        imagesList.postValue(ApiResult.Loading())
        try {
            val response = dataRepository.getImages()
            imagesList.postValue(handleImagesResponse(response))
        } catch (e: Exception) {
            Timber.e(e)
            imagesList.postValue(ApiResult.Error(e))
        }
    }

    private fun handleImagesResponse(resp: Response<ApiResponse>): ApiResult<ApiResponse> {
        return try {
            if (resp.isSuccessful) {
                if (resp.body()?.rows?.isNotEmpty() == true) {
                    Timber.d(resp.body()!!.rows.toString())
                    ApiResult.Success(resp.body()!!)
                } else {
                    ApiResult.Failure("No Data")
                }
            } else {
                ApiResult.Failure(resp.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }


}