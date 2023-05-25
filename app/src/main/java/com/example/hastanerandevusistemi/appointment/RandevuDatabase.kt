package com.example.hastanerandevusistemi.appointment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hastanerandevusistemi.json.dao.*
import com.example.hastanerandevusistemi.json.entity.*

@Database(
    entities = [
        RandevuEntity::class,
        CityEntity::class,
        DistrictEntity::class,
        HastaneEntity::class,
        PoliklinikEntity::class,
        DoktorEntity::class,
        GunEntity::class,
        SaatEntity::class],
    version = 1
)
abstract class RandevuDatabase : RoomDatabase() {

    abstract fun randevuDao(): RandevuDao
    abstract fun cityDao(): CityDao
    abstract fun districtDao(): DistrictDao
    abstract fun hastaneDao(): HastaneDao
    abstract fun poliklinikDao(): PoliklinikDao
    abstract fun doktorDao(): DoktorDao
    abstract fun gunDao(): GunDao
    abstract fun saatDao(): SaatDao

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
