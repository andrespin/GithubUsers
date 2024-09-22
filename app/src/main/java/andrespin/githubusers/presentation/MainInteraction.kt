package andrespin.githubusers.presentation

import andrespin.githubusers.base.Intent
import andrespin.githubusers.base.State
import andrespin.githubusers.domain.entity.ReposAndUsersData

sealed class MainState : State {

    data object Loading : MainState()

    data class ShowData(val data: List<ReposAndUsersData>) : MainState()

}

sealed class MainIntent : Intent {

    data object GetData : MainIntent()

}