package dev.maruffirdaus.mybooks.data.model

import com.google.gson.annotations.SerializedName

data class ImageLinks(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("smallThumbnail")
    val smallThumbnail: String? = null
)

data class Volume(

    @field:SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,

    @field:SerializedName("id")
    val id: String
)

data class VolumeInfo(

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("imageLinks")
    val imageLinks: ImageLinks? = null,

    @field:SerializedName("authors")
    val authors: List<String?>? = null,

    @field:SerializedName("infoLink")
    val infoLink: String? = null
)