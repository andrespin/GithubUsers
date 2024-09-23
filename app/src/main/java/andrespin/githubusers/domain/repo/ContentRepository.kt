package andrespin.githubusers.domain.repo

import andrespin.githubusers.domain.entity.Content
import andrespin.githubusers.domain.entity.Result

interface ContentRepository {

    suspend fun getContent(userName: String, repoName: String): Result<Content>

    suspend fun getDirContent(dir: String): Result<Content>

}