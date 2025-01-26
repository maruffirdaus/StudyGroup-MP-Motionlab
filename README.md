# Assignment Week-7

<image src="https://github.com/user-attachments/assets/b3ef2c62-5369-4a66-b289-cdb00d6aaff0" height="512px"> &nbsp;
<image src="https://github.com/user-attachments/assets/a2d32f0c-840e-4bc9-bde9-5f599d470f77" height="512px">

## Implementasi Room

- Volume

  ```
  // ~/data/model/Volume.kt
  
  @Entity
  data class Volume(
  
      @field:SerializedName("volumeInfo")
      @Embedded
      val volumeInfo: VolumeInfo,
  
      @field:SerializedName("id")
      @PrimaryKey
      val id: String
  )
  ```

  ```
  // ~/data/model/Volume.kt
  
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
  ```

  ```
  // ~/data/model/Volume.kt

  data class ImageLinks(

      @field:SerializedName("thumbnail")
      val thumbnail: String? = null,
  
      @field:SerializedName("smallThumbnail")
      val smallThumbnail: String? = null
  )
  ```

- VolumeDao

  ```
  // ~/data/local/VolumeDao.kt

  @Dao
  interface VolumeDao {
      @Upsert
      suspend fun upsert(volume: Volume)
  
      @Query("SELECT * FROM volume")
      suspend fun getVolumes(): List<Volume>
  
      @Delete
      suspend fun delete(volume: Volume)
  }
  ```

- MyBooksDatabase

  ```
  // ~/data/local/MyBooksDatabase.kt

  @Database(entities = [Volume::class], version = 1, exportSchema = false)
  @TypeConverters(Converters::class)
  abstract class MyBooksDatabase : RoomDatabase() {
      abstract fun volumeDao(): VolumeDao
  
      companion object {
          @Volatile
          private var INSTANCE: MyBooksDatabase? = null
  
          @JvmStatic
          fun getDatabase(context: Context): MyBooksDatabase {
              if (INSTANCE == null) {
                  synchronized(MyBooksDatabase::class.java) {
                      INSTANCE = Room.databaseBuilder(
                          context.applicationContext,
                          MyBooksDatabase::class.java,
                          "my_books_database"
                      ).build()
                  }
              }
              return INSTANCE as MyBooksDatabase
          }
      }
  }
  ```

- addToFavorites()

  ```
  // ~/ui/home/HomeScreen.kt

  fun addToFavorites(volume: Volume) {
      scope.launch {
          MyBooksDatabase.getDatabase(context).volumeDao().upsert(volume)
      }
  }
  ```

- Mengambil data dari database

  ```
  // ~/ui/favorites/FavoritesScreen.kt

  LaunchedEffect(Unit) {
      volumes = MyBooksDatabase.getDatabase(context).volumeDao().getVolumes()
      isLoading = false
  }
  ```

- removeFromFavorites()

  ```
  // ~/ui/favorites/FavoritesScreen.kt

  fun removeFromFavorites(volume: Volume) {
      scope.launch {
          MyBooksDatabase.getDatabase(context).volumeDao().delete(volume)
          volumes = MyBooksDatabase.getDatabase(context).volumeDao().getVolumes()
      }
  }
  ```
