package andrespin.githubusers.domain.entity

interface Data{
    val areUsers: Boolean
    val avatar_url_users: String?
    val score_users: Double?
    val login_users: String?
    val name_repos: String?
    val forks_count_repos: Int?
    val description_repos: String?
    val html_url_user: String?
    val full_name_repo: String?
}