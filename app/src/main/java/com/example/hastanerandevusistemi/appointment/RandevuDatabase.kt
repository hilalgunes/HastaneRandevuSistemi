package com.example.hastanerandevusistemi.appointment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hastanerandevusistemi.json.dao.*
import com.example.hastanerandevusistemi.json.entity.*
import com.example.hastanerandevusistemi.register.RegisterDao
import com.example.hastanerandevusistemi.register.RegisterEntity

@Database(
    entities = [
        RandevuEntity::class,
        CityEntity::class,
        DistrictEntity::class,
        HastaneEntity::class,
        PoliklinikEntity::class,
        DoktorEntity::class,
        GunEntity::class,
        SaatEntity::class,
        RegisterEntity::class],
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
    abstract fun registerDao(): RegisterDao

}
