package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.Data
import andrespin.githubusers.domain.entity.ReposAndUsersData

class ConvertToReposAndUsersDataUseCase {

    suspend fun invoke(data: List<Data>) : List<ReposAndUsersData> =
        convert(data)

    private suspend fun convert(data: List<Data>): MutableList<ReposAndUsersData> {
        val list = mutableListOf<ReposAndUsersData>()
        for (i in 0 until data.size) {
            list.add(
                ReposAndUsersData(
                    data[i].areUsers,
                    data[i].avatar_url_users,
                    data[i].score_users,
                    data[i].login_users,
                    data[i].name_repos,
                    data[i].forks_count_repos,
                    data[i].description_repos,
                    data[i].html_url_user,
                    data[i].full_name_repo
                )
            )
        }
        return list
    }

}