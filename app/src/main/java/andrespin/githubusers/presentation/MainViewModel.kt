package andrespin.githubusers.presentation

import andrespin.githubusers.base.AppViewModel
import andrespin.githubusers.base.BaseViewModel
import kotlinx.coroutines.Job

class MainViewModel  : AppViewModel<MainIntent, MainState>() {
    override fun handleIntent(): Job {
        TODO("Not yet implemented")
    }

    override val vmTag: String
        get() = TODO("Not yet implemented")


}