package com.giridharaspk.imagesapplication.data.model.response

import com.giridharaspk.imagesapplication.data.model.ImageDetails
import java.io.Serializable

data class ApiResponse(val title: String?, val rows: ArrayList<ImageDetails?>?) : Serializable
