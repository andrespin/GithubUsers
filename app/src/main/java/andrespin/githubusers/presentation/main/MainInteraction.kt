package andrespin.githubusers.presentation.main

import andrespin.githubusers.presentation.base.Intent
import andrespin.githubusers.presentation.base.State
import andrespin.githubusers.domain.entity.ReposAndUsersData

sealed class MainState : State {

    data object Loading : MainState()

    data class ShowData(val data: List<ReposAndUsersData>) : MainState()

}

sealed class MainIntent : Intent {

    data object GetData : MainIntent()

}