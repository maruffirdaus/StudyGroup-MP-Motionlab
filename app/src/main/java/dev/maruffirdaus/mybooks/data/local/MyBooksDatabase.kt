package dev.maruffirdaus.mybooks.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.maruffirdaus.mybooks.data.model.Converters
import dev.maruffirdaus.mybooks.data.model.Volume

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