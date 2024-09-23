package andrespin.githubusers.presentation.repo

import andrespin.githubusers.base.AppViewModel
import andrespin.githubusers.domain.usecase.GetContentUseCase
import andrespin.githubusers.domain.usecase.GetDirContentUseCase
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel
    @Inject constructor(
        private val getDirContentUseCase: GetDirContentUseCase,
        private val getContentUseCase: GetContentUseCase)
    : AppViewModel<RepoIntent, RepoState>(){

    override val vmTag: String
        get() = "RepoViewModel"

    init {
        handleIntent()
    }

    override fun handleIntent() = viewModelScope.launch {
        getIntent.collectLatest {
            when (it) {
                is RepoIntent.GetData -> getData()
                is RepoIntent.OpenDir -> openDir(it.dir)
            }
        }
    }

    private fun openDir(dir: String) {
        viewModelScope.launch {
            val content =  getDirContentUseCase.invoke(
                dir
            )
            emitState.emit(RepoState.ShowData(content))
        }
    }

    private fun getData() {
        viewModelScope.launch {
            val content =  getContentUseCase.invoke(
                "andrespin",
                "OnlineDictionary"
            )
           emitState.emit(RepoState.ShowData(content))
        }
    }
}