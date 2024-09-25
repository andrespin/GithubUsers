package andrespin.githubusers.presentation.repo

import andrespin.githubusers.presentation.base.BaseFragment
import andrespin.githubusers.databinding.FragmentRepoBinding
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.presentation.repo.adapter.ContentAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoFragment : RepoFragmentAbstract<FragmentRepoBinding, RepoViewModel>() {

    lateinit var adapter: ContentAdapter

    override val viewModelClass: Class<RepoViewModel>
        get() = RepoViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRepoBinding
        get() = FragmentRepoBinding::inflate

    override val frTag: String
        get() = "RepoFragment"

    override fun init() {
        initAll(this)
    }

    override fun observeViewModel() =  lifecycleScope.launch {
        model.state.collectLatest {
            when (it) {
                RepoState.Loading -> showLoading()
                is RepoState.ShowData -> showData(it.data)
                RepoState.MoveBack -> moveBack()
            }
        }
    }
}