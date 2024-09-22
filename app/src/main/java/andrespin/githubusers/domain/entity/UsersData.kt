package andrespin.githubusers.domain.entity

data class UsersData(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
) {
    data class Item(
        val avatar_url: String,
        val events_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val gravatar_id: String,
        val html_url: String,
        val id: Int,
        val login: String,
        val node_id: String,
        val organizations_url: String,
        val received_events_url: String,
        val repos_url: String,
        val score: Double,
        val site_admin: Boolean,
        val starred_url: String,
        val subscriptions_url: String,
        val type: String,
        val url: String
    ) : Data {
        override val areUsers: Boolean
            get() = true
        override val avatar_url_users: String?
            get() = this.avatar_url
        override val score_users: Double?
            get() = this.score
        override val login_users: String?
            get() = this.login
        override val name_repos: String?
            get() = null
        override val forks_count_repos: Int?
            get() = null
        override val description_repos: String?
            get() = null
        override val html_url_user: String?
            get() = html_url
    }
}

