package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.domain.entity.Result
import andrespin.githubusers.domain.repo.ContentRepository
import android.util.Log

class GetContentUseCase(
    private val contentRepository: ContentRepository
) {
    // https://api.github.com/repos/andrespin/OnlineDictionary/contents
    suspend fun invoke(userName: String, repoName: String) :List<ContentItem> {
        when (val response =
            contentRepository.getContent(
                userName, repoName
            )
        ) {
            is Result.Success -> return response.data
            is Result.Error -> throw Exception(response.exception.message.toString())
        }
    }
}