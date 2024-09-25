package andrespin.githubusers.presentation.main

import andrespin.githubusers.presentation.base.Intent
import andrespin.githubusers.presentation.base.State
import andrespin.githubusers.domain.entity.ReposAndUsersData

sealed class MainState : State {

    data object Loading : MainState()

    data object ShowError: MainState()

    data class ShowData(val data: List<ReposAndUsersData>) : MainState()

}

sealed class MainIntent : Intent {

    data class GetData(val str_to_search: String) : MainIntent()

}