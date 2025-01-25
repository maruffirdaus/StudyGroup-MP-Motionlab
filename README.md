# Assignment Week-6

<image src="https://github.com/user-attachments/assets/59545c22-9e5e-420f-b8d4-188ff9623e3e" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/9b9fc5fd-2cca-4c36-ae45-784fdc2e8c5b" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/3fe5c9e4-c5bd-4ae8-9a57-a4eeb6f626e5" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/84c06523-22fd-4f1b-9513-fb54764140a4" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/02bbb6f0-5ea4-4545-9081-1ad73ac13462" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/8f56c4fb-f9b3-4ba1-a978-6e4ef459d5a9" height="512px"> &nbsp;

## Google Books API

Google Books API merupakan API untuk mengakses data atau fitur-fitur yang ada pada Google Books seperti mencari buku dan lain sebagainya. Informasi lebih lanjut mengenai API ini: https://developers.google.com/books/docs/overview/.

## Implementasi Retrofit

- ApiService

  ```
  // ~/data/remote/retrofit/ApiService.kt
  
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
  ```

- ApiConfig

  ```
  // ~/data/remote/retrofit/ApiConfig.kt

  object ApiConfig {
      private const val BASE_URL = "https://www.googleapis.com/books/v1/"
  
      fun getApiService(): ApiService {
          val loggingInterceptor =
              HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
          val client = OkHttpClient.Builder()
              .addInterceptor(loggingInterceptor)
              .build()
          val retrofit = Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build()
  
          return retrofit.create(ApiService::class.java)
      }
  }
  ```

- getVolumes()

  ```
  // ~/ui/home/HomeScreen.kt

  fun getVolumes(query: String) {
      isLoading = true
  
      val client = ApiConfig.getApiService().getVolumes(query)
  
      client.enqueue(object : Callback<VolumesResponse> {
          override fun onResponse(
              call: Call<VolumesResponse>,
              response: Response<VolumesResponse>
          ) {
              isLoading = false
              if (response.isSuccessful) {
                  val responseBody = response.body()
  
                  if (responseBody != null) {
                      volumes = responseBody.items
                      if (responseBody.totalItems == 0) {
                          message = context.getString(R.string.keyword_unmatch_message)
                      }
                  }
              } else {
                  volumes = emptyList()
                  message = response.message()
              }
          }
  
          override fun onFailure(call: Call<VolumesResponse>, response: Throwable) {
              isLoading = false
              volumes = emptyList()
              message = response.message.toString()
          }
      })
  }
  ```
