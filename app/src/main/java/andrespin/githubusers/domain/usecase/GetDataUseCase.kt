package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.Data
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.domain.repo.ReposRepository
import andrespin.githubusers.domain.repo.UsersRepository
import andrespin.githubusers.domain.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetDataUseCase(
    private val convert: ConvertToReposAndUsersDataUseCase,
    private val usersRepository: UsersRepository,
    private val reposRepository: ReposRepository
) {
    suspend fun invoke(login: String): Flow<List<ReposAndUsersData>> = flow {
        val res = getUsers(login) + getRepos(login)
        emit(res)
    }.map { conv(it) }

    private suspend fun getUsers(login: String) = getUsersFlow(login).catch {
        throw Exception(it)
    }.first()

    private suspend fun getRepos(login: String) = getReposFlow(login).catch {
        throw Exception(it)
    }.first()

    private suspend fun getReposFlow(login: String) = flow {
        when (val response = reposRepository.getRepos(login)) {
            is Result.Success -> emit(response.data.items)
            is Result.Error -> throw Exception(response.exception.message.toString())
        }
    }

    private suspend fun getUsersFlow(login: String) = flow {
        when (val response = usersRepository.getUsers(login)) {
            is Result.Success -> emit(response.data.items)
            is Result.Error -> throw Exception(response.exception.message.toString())
        }
    }

    private suspend fun conv(data: List<Data>): List<ReposAndUsersData> = convert.invoke(data)

}