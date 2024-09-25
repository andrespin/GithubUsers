package andrespin.githubusers.presentation.repo

import andrespin.githubusers.presentation.base.AppViewModel
import andrespin.githubusers.domain.usecase.GetContentUseCase
import andrespin.githubusers.domain.usecase.GetDirContentUseCase
import android.util.Log
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
                is RepoIntent.GetData -> getData(it.full_name_repo)
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

    private fun getData(fullNameRepo: String) {
        Log.d(vmTag, fullNameRepo)
        viewModelScope.launch {
            val content =  getContentUseCase.invoke(
                "andrespin",
                "OnlineDictionary"
            )
           emitState.emit(RepoState.ShowData(content))
        }
    }
}