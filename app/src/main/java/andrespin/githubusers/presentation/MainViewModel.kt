package andrespin.githubusers.presentation

import andrespin.githubusers.base.AppViewModel
import andrespin.githubusers.base.BaseViewModel
import andrespin.githubusers.domain.usecase.GetDataUseCase
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : AppViewModel<MainIntent, MainState>() {

    override val vmTag: String
        get() = "MainViewModel"

    init {
        handleIntent()
    }

    override fun handleIntent() = viewModelScope.launch {
        getIntent.collectLatest {
            when (it) {
                is MainIntent.GetData -> getData()
            }
        }
    }

    private fun getData() {
        Log.d(vmTag, "getData()")
        viewModelScope.launch {
            getDataUseCase.invoke("andrespin")
        }
    }

}