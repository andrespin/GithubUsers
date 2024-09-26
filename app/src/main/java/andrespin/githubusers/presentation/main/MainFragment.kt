package andrespin.githubusers.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import andrespin.githubusers.databinding.FragmentMainBinding
import androidx.lifecycle.lifecycleScope
import com.google.android.material.internal.ViewUtils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : MainFragmentAbstract<FragmentMainBinding, MainViewModel>() {

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override val frTag: String
        get() = "MainFragment"

    override fun init() {
        initAll(this)
    }

    override fun onResumeFragment() {
        downloadEditTextRequest()
    }

    override fun observeViewModel() = lifecycleScope.launch {
        model.state.collectLatest {
            when (it) {
                MainState.Loading -> showLoading()
                is MainState.ShowData -> showData(it.data)
                is MainState.ShowError -> showError(it.msg)
            }
        }
    }

}