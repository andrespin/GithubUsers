package andrespin.githubusers.presentation.repo

import andrespin.githubusers.presentation.base.Intent
import andrespin.githubusers.presentation.base.State
import andrespin.githubusers.domain.entity.ContentItem

sealed class RepoState : State {

    data object Loading : RepoState()

    data object MoveBack : RepoState()

    data class ShowData(val data: List<ContentItem>) : RepoState()

}

sealed class RepoIntent : Intent {

    data object MoveBack : RepoIntent()

    data object ShowWebView : RepoIntent()

    data class GetData(val full_name_repo: String) : RepoIntent()

    data class OpenDir(val dir: String) : RepoIntent()

}