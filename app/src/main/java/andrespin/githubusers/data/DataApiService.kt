package andrespin.githubusers.data

import andrespin.githubusers.domain.ReposData
import andrespin.githubusers.domain.UsersData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface DataApiService {


    // https://api.github.com/search/users?q=andrespin

    // https://api.github.com/search/repositories?q=andrespin

    @GET("/search/users?")
    suspend fun getUsers(
        @Query("q") action: String
    ): Response<UsersData>

    @GET("/search/repositories?")
    suspend fun getRepos(
        @Query("q") action: String
    ): Response<ReposData>

}