package andrespin.githubusers.presentation.main

import andrespin.githubusers.presentation.base.AppViewModel
import andrespin.githubusers.domain.usecase.GetDataUseCase
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val d = getDataUseCase.invoke("andrespin")
            emitState.emit(MainState.ShowData(d))
        }
    }

}