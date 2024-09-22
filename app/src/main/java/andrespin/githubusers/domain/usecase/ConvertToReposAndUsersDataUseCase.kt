package andrespin.githubusers.domain.usecase

import andrespin.githubusers.domain.entity.Data
import andrespin.githubusers.domain.entity.ReposAndUsersData

class ConvertToReposAndUsersDataUseCase {

    suspend fun invoke(data: Data) : ReposAndUsersData =
        ReposAndUsersData(
            data.areUsers,
            data.avatar_url_users,
            data.score_users,
            data.login_users,
            data.name_repos,
            data.forks_count_repos,
            data.description_repos
        )
}