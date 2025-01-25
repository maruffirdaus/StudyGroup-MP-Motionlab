package dev.maruffirdaus.mybooks.data.remote.response

import com.google.gson.annotations.SerializedName
import dev.maruffirdaus.mybooks.data.model.Volume

data class VolumesResponse(

	@field:SerializedName("totalItems")
	val totalItems: Int = 0,

	@field:SerializedName("items")
	val items: List<Volume> = emptyList()
)
