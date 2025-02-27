package hr.ferit.stefanbelic.mushroom.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.ferit.stefanbelic.mushroom.repositories.MushroomRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideMushroomRepository() = MushroomRepository()

}