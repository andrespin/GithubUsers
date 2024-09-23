package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.domain.repo.ContentRepository
import andrespin.githubusers.domain.entity.Result

class GetDirContentUseCase(
    private val contentRepository: ContentRepository
) {
    suspend fun invoke(dir: String) :List<ContentItem> {
        val cutBaseUrl = dir.replace("https://api.github.com", "")
        when (val response =
            contentRepository.getDirContent(
                cutBaseUrl
            )
        ) {
            is Result.Success -> return response.data
            is Result.Error -> throw Exception(response.exception.message.toString())
        }
    }
}
