package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.UsersRepository
import andrespin.githubusers.domain.entity.Result
import android.util.Log


class GetDataUseCase(private val usersRepository: UsersRepository) {
    suspend fun invoke(login: String) {
        Log.d("GetDataUseCase", "response.exception.message.toString()")
        when(val response = usersRepository.getUsers(login)) {
            is Result.Success -> Log.d("GetDataUseCase", response.data.toString())
            is Result.Error -> Log.d("GetDataUseCase", response.exception.message.toString())
        }
    }
}