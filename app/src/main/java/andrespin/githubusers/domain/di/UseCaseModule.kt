package andrespin.githubusers.domain.di

import andrespin.githubusers.domain.repo.ReposRepository
import andrespin.githubusers.domain.repo.UsersRepository
import andrespin.githubusers.domain.usecase.ConvertToReposAndUsersDataUseCase
import andrespin.githubusers.domain.usecase.GetDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideConvertToReposAndUsersDataUseCase(): ConvertToReposAndUsersDataUseCase =
        ConvertToReposAndUsersDataUseCase()

    @Provides
    fun providesGetDataUseCase(
        convertToReposAndUsersDataUseCase: ConvertToReposAndUsersDataUseCase,
        usersRepository: UsersRepository,
        reposRepository: ReposRepository
    ) = GetDataUseCase(convertToReposAndUsersDataUseCase, usersRepository, reposRepository)

}