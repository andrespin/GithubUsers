package andrespin.githubusers.data.di

import andrespin.githubusers.data.RemoteDataSourceImpl
import andrespin.githubusers.data.repo.ContentRepositoryImpl
import andrespin.githubusers.data.repo.ReposRepositoryImpl
import andrespin.githubusers.data.repo.UsersRepositoryImpl
import andrespin.githubusers.domain.repo.ContentRepository
import andrespin.githubusers.domain.repo.ReposRepository
import andrespin.githubusers.domain.repo.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getUsersRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ) : UsersRepository = UsersRepositoryImpl(remoteDataSourceImpl)

    @Provides
    fun getReposRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ) : ReposRepository = ReposRepositoryImpl(remoteDataSourceImpl)

    @Provides
    fun getRepoContentRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ) : ContentRepository = ContentRepositoryImpl(remoteDataSourceImpl)

}
