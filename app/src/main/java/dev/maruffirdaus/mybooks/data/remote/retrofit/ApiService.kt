package dev.maruffirdaus.mybooks.data.remote.retrofit

import dev.maruffirdaus.mybooks.data.remote.response.VolumesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    fun getVolumes(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY
    ): Call<VolumesResponse>

    companion object {
        private const val API_KEY = "AIzaSyDCYGHYYv3SIs6oWMcDr8cvEotQttEYDGI"
    }
}