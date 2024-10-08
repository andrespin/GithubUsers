package andrespin.githubusers.data

import andrespin.githubusers.domain.entity.Content
import andrespin.githubusers.domain.entity.ReposData
import andrespin.githubusers.domain.entity.UsersData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface DataApiService {

    @GET("/search/users?")
    suspend fun getUsers(
        @Query("q") action: String
    ): Response<UsersData>

    @GET("/search/repositories?")
    suspend fun getRepos(
        @Query("q") action: String
    ): Response<ReposData>

    @GET
    suspend fun getRepoContent(@Url url: String?) : Response<Content?>?

}