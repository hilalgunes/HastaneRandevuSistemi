package com.example.hastanerandevusistemi

import android.content.Context
import androidx.room.Room
import com.example.hastanerandevusistemi.appointment.RandevuDao
import com.example.hastanerandevusistemi.appointment.RandevuDatabase
import com.example.hastanerandevusistemi.appointment.RandevuRepository
import com.example.hastanerandevusistemi.appointment.RandevuRepositoryImpl
import com.example.hastanerandevusistemi.json.*
import com.example.hastanerandevusistemi.json.dao.*
import com.example.hastanerandevusistemi.json.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRandevuDatabase(@ApplicationContext context: Context): RandevuDatabase {
        return Room.databaseBuilder(
            context,
            RandevuDatabase::class.java,
            "hastane_randevu_otomasyonu"
        ).build()
    }

    @Provides
    fun provideCityDao(randevuDatabase: RandevuDatabase): CityDao {
        return randevuDatabase.cityDao()
    }

    @Provides
    fun provideCityDbRepositoryImpl(dao: CityDao): CityRepository {
        return CityRepositoryImpl(dao)
    }

    @Provides
    fun provideDistrictDao(randevuDatabase: RandevuDatabase): DistrictDao {
        return randevuDatabase.districtDao()
    }

    @Provides
    fun provideDistrictDbRepositoryImpl(dao: DistrictDao): DistrictRepository {
        return DistrictRepositoryImpl(dao)
    }

    @Provides
    fun provideHastaneDao(randevuDatabase: RandevuDatabase): HastaneDao {
        return randevuDatabase.hastaneDao()
    }

    @Provides
    fun provideHastaneDbRepositoryImpl(dao: HastaneDao): HastaneRepository {
        return HastaneRepositoryImpl(dao)
    }

    @Provides
    fun providePoliklinikcDao(randevuDatabase: RandevuDatabase): PoliklinikDao {
        return randevuDatabase.poliklinikDao()
    }

    @Provides
    fun providePoliklinikcDbRepositoryImpl(dao: PoliklinikDao): PoliklinikRepository {
        return PoliklinikRepositoryImpl(dao)
    }

    @Provides
    fun provideDoktorDao(randevuDatabase: RandevuDatabase): DoktorDao {
        return randevuDatabase.doktorDao()
    }

    @Provides
    fun provideDoktorDbRepositoryImpl(dao: DoktorDao): DoktorRepository {
        return DoktorRepositoryImpl(dao)
    }

    @Provides
    fun provideGunDao(randevuDatabase: RandevuDatabase): GunDao {
        return randevuDatabase.gunDao()
    }

    @Provides
    fun provideGunDbRepositoryImpl(dao: GunDao): GunRepository {
        return GunRepositoryImpl(dao)
    }

    @Provides
    fun provideSaatDao(randevuDatabase: RandevuDatabase): SaatDao {
        return randevuDatabase.saatDao()
    }

    @Provides
    fun provideSaatDbRepositoryImpl(dao: SaatDao): SaatRepository {
        return SaatRepositoryImpl(dao)
    }

    @Provides
    fun providerandevuDao(randevuDatabase: RandevuDatabase): RandevuDao {
        return randevuDatabase.randevuDao()

    }

    @Provides
    fun provideRandevuDbRepositoryImpl(dao: RandevuDao): RandevuRepository {
        return RandevuRepositoryImpl(dao)
    }
}

