package com.giridharaspk.imagesapplication.data.model

import java.io.Serializable

data class ImageDetails(
    val description: String?,
    val imageHref: String?,
    val title: String?
) : Serializable