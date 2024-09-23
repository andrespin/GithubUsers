package andrespin.githubusers.presentation.repo

import andrespin.githubusers.base.Intent
import andrespin.githubusers.base.State
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.domain.entity.ReposAndUsersData

sealed class RepoState : State {

    data object Loading : RepoState()

    data class ShowData(val data: List<ContentItem>) : RepoState()

}

sealed class RepoIntent : Intent {

    data object GetData : RepoIntent()

}