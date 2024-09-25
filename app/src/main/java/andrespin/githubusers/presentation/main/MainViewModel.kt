package andrespin.githubusers.presentation.main

import andrespin.githubusers.presentation.base.AppViewModel
import andrespin.githubusers.domain.usecase.GetDataUseCase
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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
                is MainIntent.GetData -> getData(it.str_to_search)
            }
        }
    }

    private fun getData(strToSearch: String) {
        if (strToSearch.toCharArray().size > 3) {
            showLoading()
            loadData(strToSearch)
        }
    }

    private fun showLoading() = viewModelScope.launch {
        emitState.emit(MainState.Loading)
    }

    private fun loadData(strToSearch: String) = viewModelScope.launch {
        getDataUseCase.invoke(strToSearch)
            .catch {
                emitState.emit(MainState.ShowError(it.message.toString()))
            }
            .collect {
                emitState.emit(MainState.ShowData(it))
            }
    }

}