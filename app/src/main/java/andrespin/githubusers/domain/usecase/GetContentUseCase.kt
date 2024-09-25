package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.Result
import andrespin.githubusers.domain.repo.ContentRepository
import kotlinx.coroutines.flow.flow

class GetContentUseCase(
    private val contentRepository: ContentRepository
) {
    suspend fun invoke(fullNameRepo: String) = flow {
        when (val response =
            contentRepository.getContent(
                fullNameRepo
            )
        ) {
            is Result.Success -> emit(response.data)
            is Result.Error -> throw Exception(response.exception.message.toString())
        }
    }
}
