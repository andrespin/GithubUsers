package andrespin.githubusers.data

import andrespin.githubusers.domain.entity.Content
import andrespin.githubusers.domain.entity.ReposData
import andrespin.githubusers.domain.entity.UsersData
import javax.inject.Inject
import retrofit2.Response
import andrespin.githubusers.domain.entity.Result
import android.util.Log

class RemoteDataSourceImpl
@Inject constructor(private val dataApiService: DataApiService) {

    suspend fun getUsersData(login: String): Result<UsersData> =
        getUsersResult(dataApiService.getUsers(login))

    suspend fun getReposData(name: String): Result<ReposData> =
        getReposResult(dataApiService.getRepos(name))

    suspend fun getRepoContent(fullNameRepo: String) = getRepoContentResult(
        dataApiService.getRepoContent("/repos/$fullNameRepo/contents")
    )

    suspend fun getRepoDirContent(dir: String): Result<Content> = getRepoContentResult(
            dataApiService.getRepoContent(dir)
        )

    private fun getRepoContentResult(apiResult: Response<Content?>?) = if (apiResult!!.isSuccessful) {
            Result.Success(apiResult.body()!!)
        } else {
            Result.Error(Exception(apiResult.errorBody().toString()))
        }

    private fun getUsersResult(apiResult: Response<UsersData>) = if (apiResult.isSuccessful) {
        Result.Success(apiResult.body()!!)
    } else {
        Result.Error(Exception(apiResult.errorBody().toString()))
    }

    private fun getReposResult(apiResult: Response<ReposData>) = if (apiResult.isSuccessful) {
        Result.Success(apiResult.body()!!)
    } else {
        Result.Error(Exception(apiResult.errorBody().toString()))
    }
}

