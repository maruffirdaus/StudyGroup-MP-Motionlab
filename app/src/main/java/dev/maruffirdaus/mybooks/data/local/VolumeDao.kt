package dev.maruffirdaus.mybooks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.maruffirdaus.mybooks.data.model.Volume

@Dao
interface VolumeDao {
    @Upsert
    suspend fun upsert(volume: Volume)

    @Query("SELECT * FROM volume")
    suspend fun getVolumes(): List<Volume>

    @Delete
    suspend fun delete(volume: Volume)
}