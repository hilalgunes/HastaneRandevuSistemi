package com.example.hastanerandevusistemi.appointment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RandevuEntity::class], version = 1)
abstract class RandevuDatabase : RoomDatabase() {

    abstract fun randevuDao(): RandevuDao

    companion object {
        @Volatile
        private var instance: RandevuDatabase? = null

        fun getInstance(context: Context): RandevuDatabase {
            return instance ?: synchronized(this) {
                val databaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    RandevuDatabase::class.java,
                    "randevu_database"
                )
                instance = databaseBuilder.build()
                instance!!
            }
        }
    }
}
