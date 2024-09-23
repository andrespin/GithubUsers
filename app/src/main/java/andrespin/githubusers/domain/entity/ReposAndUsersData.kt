package andrespin.githubusers.domain.entity

data class ReposAndUsersData(
    val areUsers: Boolean,
    val avatar_url_user: String? = null,
    val score_user: Double? = null,
    val login_user: String?= null,
    val name_repo: String?= null,
    val forks_count_repo: Int? = null,
    val description_repo: String? = null,
    val html_url_user: String? = null,
    val full_name_repo: String? = null
)
