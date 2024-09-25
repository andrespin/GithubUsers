package andrespin.githubusers.presentation.repo

import andrespin.githubusers.presentation.base.AppViewModel
import andrespin.githubusers.domain.usecase.GetContentUseCase
import andrespin.githubusers.domain.usecase.GetDirContentUseCase
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
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

    private val dirList = mutableListOf<String>()

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
                RepoIntent.MoveBack -> moveBack()
            }
        }
    }

    private fun moveBack() {
        if (dirList.size >= 2) {
            viewModelScope.launch {
                val content = getDirContentUseCase.invoke(
                    dirList[dirList.lastIndex - 1]
                )
                Log.d(vmTag, "dirList[dirList.lastIndex] = ${dirList[dirList.lastIndex]}, content = $content")
                Log.d(vmTag, "dirList = ${dirList}")
                emitState.emit(RepoState.ShowData(content))
                dirList.removeAt(dirList.lastIndex)

            }
        } else {
            viewModelScope.launch {
                emitState.emit(RepoState.MoveBack)
            }
        }
    }

    private fun openDir(dir: String) {
        dirList.add(dir)
        viewModelScope.launch {
            val content =  getDirContentUseCase.invoke(
                dir
            )
            emitState.emit(RepoState.ShowData(content))
        }
    }

    private fun getData(fullNameRepo: String) {
        val dir  = "https://api.github.com/repos/$fullNameRepo/contents"
        dirList.add(dir)
        Log.d(vmTag, fullNameRepo)
        viewModelScope.launch {
            val content =  getContentUseCase.invoke(
                fullNameRepo
            )
           emitState.emit(RepoState.ShowData(content))
        }
    }
}