package andrespin.githubusers.domain.repo

import andrespin.githubusers.domain.entity.ReposData
import andrespin.githubusers.domain.entity.Result

interface ReposRepository {

    suspend fun getRepos(name: String): Result<ReposData>

}