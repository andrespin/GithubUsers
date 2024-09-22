package andrespin.githubusers.domain.repo

import andrespin.githubusers.domain.entity.Result
import andrespin.githubusers.domain.entity.UsersData

interface UsersRepository {

    suspend fun getUsers(login: String): Result<UsersData>

}