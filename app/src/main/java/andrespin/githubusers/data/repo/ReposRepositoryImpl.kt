package andrespin.githubusers.data.repo

import andrespin.githubusers.data.RemoteDataSourceImpl
import andrespin.githubusers.domain.ReposData
import andrespin.githubusers.domain.ReposRepository
import andrespin.githubusers.domain.entity.Result

class ReposRepositoryImpl(private val remoteDataSourceImpl: RemoteDataSourceImpl): ReposRepository {
    override suspend fun getRepos(name: String): Result<ReposData> =
        remoteDataSourceImpl.getReposData(name)
}