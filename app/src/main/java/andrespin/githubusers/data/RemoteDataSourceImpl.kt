package andrespin.githubusers.data

import andrespin.githubusers.domain.UsersData
import javax.inject.Inject
import retrofit2.Response
import andrespin.githubusers.domain.entity.Result

class RemoteDataSourceImpl
    @Inject constructor(private val dataApiService: DataApiService) {

    suspend fun getUsersData(login: String): Result<UsersData> =
        getUsersResult(dataApiService.getUsers(login))

    private fun getUsersResult(apiResult: Response<UsersData>) = if (apiResult.isSuccessful) {
        Result.Success(apiResult.body()!!)
    } else {
        Result.Error(Exception(apiResult.errorBody().toString()))
    }
}

