package andrespin.githubusers.presentation.repo

import andrespin.githubusers.base.BaseFragment
import andrespin.githubusers.databinding.FragmentRepoBinding
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.presentation.repo.adapter.ContentAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoFragment : BaseFragment<FragmentRepoBinding, RepoViewModel>() {

    lateinit var adapter: ContentAdapter

    override val viewModelClass: Class<RepoViewModel>
        get() = RepoViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRepoBinding
        get() = FragmentRepoBinding::inflate

    override val frTag: String
        get() = "RepoFragment"

    override fun init() {
        lifecycleScope.launch { model.intent.emit(RepoIntent.GetData) }
        initAdapter()
    }

    override fun observeViewModel() =  lifecycleScope.launch {
        model.state.collectLatest {
            when (it) {
                RepoState.Loading -> showLoading()
                is RepoState.ShowData -> showData(it.data)
            }
        }
    }

    private fun showData(data: List<ContentItem>) {
        hideLoading()
        setDataToAdapter(data)
    }

    private fun initAdapter() {
        adapter = ContentAdapter()
        binding.rvContent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContent.adapter = adapter
    }

    private fun setDataToAdapter(data: List<ContentItem>) =
        adapter.setData(data)

    private fun showLoading() {

    }

    private fun hideLoading() {

    }

}