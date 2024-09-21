package andrespin.githubusers.data

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

}