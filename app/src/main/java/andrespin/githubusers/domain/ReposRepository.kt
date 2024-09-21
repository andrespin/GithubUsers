package andrespin.githubusers.domain

import andrespin.githubusers.domain.entity.Result

interface ReposRepository {

    suspend fun getRepos(name: String): Result<ReposData>

}