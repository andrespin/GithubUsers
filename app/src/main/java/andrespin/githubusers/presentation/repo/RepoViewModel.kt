package andrespin.githubusers.presentation.repo

import andrespin.githubusers.presentation.base.AppViewModel
import andrespin.githubusers.domain.usecase.GetContentUseCase
import andrespin.githubusers.domain.usecase.GetDirContentUseCase
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel
@Inject constructor(
    private val getDirContentUseCase: GetDirContentUseCase,
    private val getContentUseCase: GetContentUseCase
) : AppViewModel<RepoIntent, RepoState>() {

    private val dirList = mutableListOf<String>()

    private var isWebViewShowed = false

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
                RepoIntent.ShowWebView -> setWebViewShowed(true)
            }
        }
    }

    private fun setWebViewShowed(isWebShowed: Boolean) {
        isWebViewShowed = isWebShowed
    }

    private fun moveBack() =
        if (!isWebViewShowed) {
            if (dirList.size >= 2) {
                getDirContent()
            } else {
                sendMoveBackState()
            }
        } else {
            setWebViewShowed(false)
            if (dirList.size >= 1) {
                showDirContent()
            } else {
                sendMoveBackState()
            }
        }

    private fun showDirContent() = viewModelScope.launch {
        val content = getDirContentUseCase.invoke(
            dirList[dirList.lastIndex]
        )
        emitState.emit(RepoState.ShowData(content))
    }

    private fun getDirContent() = viewModelScope.launch {
        val content = getDirContentUseCase.invoke(
            dirList[dirList.lastIndex - 1]
        )
        emitState.emit(RepoState.ShowData(content))
        dirList.removeAt(dirList.lastIndex)
    }

    private fun sendMoveBackState() = viewModelScope.launch {
        emitState.emit(RepoState.MoveBack)
    }


    private fun openDir(dir: String) {
        addLinkToDirList(dir)
        downloadDirContentFromServer(dir)
    }

    private fun getData(fullNameRepo: String) {
        addLinkToDirList(setFullNameRepoToLink(fullNameRepo))
        downloadDataFromServer(fullNameRepo)
    }

    private fun downloadDirContentFromServer(dir: String) = viewModelScope.launch {
        val content = getDirContentUseCase.invoke(
            dir
        )
        emitState.emit(RepoState.ShowData(content))
    }

    private fun downloadDataFromServer(fullNameRepo: String) = viewModelScope.launch {
        getContentUseCase.invoke(
            fullNameRepo
        ).catch {
            it.printStackTrace()
        }.collect {
            emitState.emit(RepoState.ShowData(it))
        }
    }

    private fun addLinkToDirList(dir: String) = dirList.add(dir)

    private fun setFullNameRepoToLink(fullNameRepo: String) =
        "https://api.github.com/repos/$fullNameRepo/contents"


}