package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.repo.ReposRepository
import andrespin.githubusers.domain.repo.UsersRepository
import andrespin.githubusers.domain.entity.Result
import android.util.Log


class GetDataUseCase(
    private val usersRepository: UsersRepository,
    private val reposRepository: ReposRepository
) {
    suspend fun invoke(login: String) {

        when(val response = reposRepository.getRepos(login)) {
            is Result.Success -> Log.d("GetDataUseCase", response.data.toString())
            is Result.Error -> Log.d("GetDataUseCase", response.exception.message.toString())
        }

        when(val response = usersRepository.getUsers(login)) {
            is Result.Success -> Log.d("GetDataUseCase", response.data.toString())
            is Result.Error -> Log.d("GetDataUseCase", response.exception.message.toString())
        }

    }
}