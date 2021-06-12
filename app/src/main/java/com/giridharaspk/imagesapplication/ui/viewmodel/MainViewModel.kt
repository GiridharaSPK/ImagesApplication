package com.giridharaspk.imagesapplication.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.giridharaspk.imagesapplication.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application, val dataRepository: DataRepository) : AndroidViewModel(app) {


    fun getImages() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun handleApiResponse(){

    }

}