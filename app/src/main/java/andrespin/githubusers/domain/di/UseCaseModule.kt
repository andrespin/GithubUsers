package andrespin.githubusers.domain.di

import andrespin.githubusers.domain.repo.ReposRepository
import andrespin.githubusers.domain.repo.UsersRepository
import andrespin.githubusers.domain.usecase.GetDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun providesGetDataUseCase(
        usersRepository: UsersRepository,
        reposRepository: ReposRepository
    ) = GetDataUseCase(usersRepository, reposRepository)

}