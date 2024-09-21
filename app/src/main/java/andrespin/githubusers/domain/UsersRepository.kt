package andrespin.githubusers.domain

import andrespin.githubusers.domain.entity.Result

interface UsersRepository {

    suspend fun getUsers(login: String): Result<UsersData>

}