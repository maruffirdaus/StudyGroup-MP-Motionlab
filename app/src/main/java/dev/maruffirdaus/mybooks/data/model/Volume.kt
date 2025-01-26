package dev.maruffirdaus.mybooks.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class ImageLinks(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("smallThumbnail")
    val smallThumbnail: String? = null
)

@Entity
data class Volume(

    @field:SerializedName("volumeInfo")
    @Embedded
    val volumeInfo: VolumeInfo,

    @field:SerializedName("id")
    @PrimaryKey
    val id: String
)

data class VolumeInfo(

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("imageLinks")
    @Embedded
    val imageLinks: ImageLinks? = null,

    @field:SerializedName("authors")
    val authors: List<String?>? = null,

    @field:SerializedName("infoLink")
    val infoLink: String? = null
)

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromList(value: List<String?>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value: String?): List<String?>? {
        val type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(value, type)
    }
}