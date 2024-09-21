package andrespin.githubusers.presentation

import andrespin.githubusers.base.Intent
import andrespin.githubusers.base.State

sealed class MainState : State {

    data object Loading : MainState()

}

sealed class MainIntent : Intent {

    data object GetData : MainIntent()

}