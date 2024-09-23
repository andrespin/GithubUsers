package andrespin.githubusers.presentation.repo

import andrespin.githubusers.base.AppViewModel
import andrespin.githubusers.data.DataApiService
import andrespin.githubusers.domain.entity.Content
import andrespin.githubusers.domain.entity.Result
import andrespin.githubusers.domain.usecase.GetContentUseCase
import andrespin.githubusers.domain.usecase.GetDataUseCase
import andrespin.githubusers.presentation.main.MainIntent
import andrespin.githubusers.presentation.main.MainState
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class RepoViewModel
    @Inject constructor(private val getContentUseCase: GetContentUseCase)
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
            }
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