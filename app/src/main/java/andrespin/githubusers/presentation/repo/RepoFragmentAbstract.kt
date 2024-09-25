package andrespin.githubusers.presentation.repo

import andrespin.githubusers.databinding.FragmentRepoBinding
import andrespin.githubusers.domain.entity.ContentItem
import andrespin.githubusers.presentation.base.BaseFragment
import andrespin.githubusers.presentation.repo.adapter.ContentAdapter
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

abstract class RepoFragmentAbstract<VB : FragmentRepoBinding, VM : RepoViewModel> :
    BaseFragment<VB, VM>() {

    private lateinit var adapter: ContentAdapter

    protected fun initAll(fragment: RepoFragment) {
        initBackBtnClickListener()
        initAdapter(fragment)
        sendGetDataIntent(getArgumentsFromBundle())
    }

    protected fun moveBack() {
        findNavController().popBackStack()
    }

    protected fun showData(data: List<ContentItem>) {
        hideAll()
        showAdapter()
        setDataToAdapter(data)
    }

    protected fun showLoading() {
        hideAll()
        setLoadingVisible()
    }

    private fun setLoadingVisible() {
        binding.progressBarRepo.visibility = View.VISIBLE
    }

    private fun showAdapter() {
        binding.rvContent.visibility = View.VISIBLE
    }

    private fun setDataToAdapter(data: List<ContentItem>) =
        adapter.setData(data)

    private fun getArgumentsFromBundle() = arguments?.getString("full_name_repo")

    private fun sendGetDataIntent(args: String?) =
        lifecycleScope.launch {
            if (args != null) model.intent.emit(RepoIntent.GetData((args)))
        }

    private fun initAdapter(fragment: RepoFragment) {
        adapter = ContentAdapter(
            fragment,
            binding.web, binding.rvContent
        )
        binding.rvContent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContent.adapter = adapter
    }

    private fun hideAll() {
        binding.web.visibility = View.GONE
        binding.rvContent.visibility = View.GONE
        binding.progressBarRepo.visibility = View.GONE
    }

    private fun initBackBtnClickListener() =
        binding.btnBack.setOnClickListener {
            lifecycleScope.launch {
                model.intent.emit(RepoIntent.MoveBack)
            }
        }
}