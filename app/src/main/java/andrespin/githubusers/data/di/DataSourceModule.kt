package andrespin.githubusers.data.di

import andrespin.githubusers.data.DataApiService
import andrespin.githubusers.data.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideData(dataApiService: DataApiService)
            : RemoteDataSourceImpl = RemoteDataSourceImpl(dataApiService)
}