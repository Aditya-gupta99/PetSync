package com.sparklead.petsync.di

import com.sparklead.petsync.home.RaspberryPiRepository
import com.sparklead.petsync.home.RaspberryPiRepositoryImp
import com.sparklead.petsync.service.RaspberryPiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryComponent {

    @Provides
    @Singleton
    fun providesRaspberryPiRepository(raspberryPiService: RaspberryPiService): RaspberryPiRepository =
        RaspberryPiRepositoryImp(raspberryPiService)

}