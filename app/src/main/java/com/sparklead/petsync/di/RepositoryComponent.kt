package com.sparklead.petsync.di

import com.sparklead.petsync.service.FeedService
import com.sparklead.petsync.ui.home.RaspberryPiRepository
import com.sparklead.petsync.ui.home.RaspberryPiRepositoryImp
import com.sparklead.petsync.service.RaspberryPiService
import com.sparklead.petsync.ui.history.HistoryRepository
import com.sparklead.petsync.ui.history.HistoryRepositoryImp
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

    @Provides
    @Singleton
    fun providesHistoryRepository(feedService: FeedService) : HistoryRepository = HistoryRepositoryImp(feedService)

}