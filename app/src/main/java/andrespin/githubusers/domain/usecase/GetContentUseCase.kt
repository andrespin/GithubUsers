package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.domain.entity.Result
import andrespin.githubusers.domain.repo.ContentRepository
import android.util.Log
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


//suspend fun invoke(fullNameRepo: String) :List<ContentItem> {
//    when (val response =
//        contentRepository.getContent(
//            fullNameRepo
//        )
//    ) {
//        is Result.Success -> return response.data
//        is Result.Error -> throw Exception(response.exception.message.toString())
//    }
//}