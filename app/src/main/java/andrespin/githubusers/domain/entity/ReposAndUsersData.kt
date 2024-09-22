package andrespin.githubusers.domain.entity

data class ReposAndUsersData(
    val areUsers: Boolean,
    val avatar_url: String? = null,
    val score: Double? = null,
    val login: String?= null,
    val name: String?= null,
    val forks_count: Int? = null,
    val description: String? = null,
    val html_url_user: String? = null
)
