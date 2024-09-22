package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.Data
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.domain.repo.ReposRepository
import andrespin.githubusers.domain.repo.UsersRepository
import andrespin.githubusers.domain.entity.Result
import andrespin.githubusers.domain.entity.UsersData
import android.util.Log
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge


class GetDataUseCase(
    private val convert: ConvertToReposAndUsersDataUseCase,
    private val usersRepository: UsersRepository,
    private val reposRepository: ReposRepository
) {
    suspend fun invoke(login: String): List<ReposAndUsersData> {

        val reposFlow = flow {
            when (val response = reposRepository.getRepos(login)) {
                is Result.Success -> emit(response.data.items)
                is Result.Error -> throw Exception(response.exception.message.toString())
            }
        }
        val usersFlow = flow {
            when (val response = usersRepository.getUsers(login)) {
                is Result.Success -> emit(response.data.items)
                is Result.Error -> throw Exception(response.exception.message.toString())
            }
        }

        val list = reposFlow.first() + usersFlow.first()

        return conv(list)

    }




    private suspend fun conv(data: List<Data>): List<ReposAndUsersData> {
        Log.d("GetDataUseCase convert.invoke(data)", convert.invoke(data).toString())
        return convert.invoke(data)
    }



}