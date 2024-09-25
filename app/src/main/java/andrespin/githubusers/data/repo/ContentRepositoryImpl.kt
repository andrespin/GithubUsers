package andrespin.githubusers.data.repo

import andrespin.githubusers.data.RemoteDataSourceImpl
import andrespin.githubusers.domain.entity.Content
import andrespin.githubusers.domain.repo.ContentRepository
import andrespin.githubusers.domain.entity.Result

class ContentRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) : ContentRepository {

    override suspend fun getContent(fullNameRepo: String): Result<Content> =
        remoteDataSourceImpl.getRepoContent(fullNameRepo)

    override suspend fun getDirContent(dir: String): Result<Content> =
        remoteDataSourceImpl.getRepoDirContent(dir)

}