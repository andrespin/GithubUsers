package andrespin.githubusers.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import andrespin.githubusers.presentation.base.BaseFragment
import andrespin.githubusers.databinding.FragmentMainBinding
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.presentation.main.adapter.repos_and_users.DataAdapter
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    lateinit var adapter: DataAdapter

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override val frTag: String
        get() = "MainFragment"

    override fun init() {
        initAdapter()
        initTryAgainClickListener()
        initSearchClickListener()
        downloadEditTextRequest()
        setFocusableOn()
    }

    override fun onResumeFragment() = downloadEditTextRequest()

    private fun downloadEditTextRequest() {
        val search = binding.layoutSearchView.editUser.text.toString()
        lifecycleScope.launch { model.intent.emit(MainIntent.GetData(search)) }
        Log.d(frTag, "downloadEditTextRequest() $search")
    }

    private fun setFocusableOn() {
        binding.layoutSearchView.imgSearch.isFocusable = true
        binding.layoutSearchView.editUser.isFocusable = true
    }

    private fun setFocusableOff() {
        binding.layoutSearchView.editUser.isFocusable = false
        binding.layoutSearchView.imgSearch.isFocusable = false
    }

    private fun initTryAgainClickListener() =
        binding.layoutMainSearchShowError.btnMainTryAgain.setOnClickListener {
            val search = binding.layoutSearchView.editUser.text.toString()
            lifecycleScope.launch { model.intent.emit(MainIntent.GetData(search)) }
        }

    private fun initSearchClickListener() =
        binding.layoutSearchView.imgSearch.setOnClickListener {
                val search = binding.layoutSearchView.editUser.text.toString()
                lifecycleScope.launch { model.intent.emit(MainIntent.GetData(search)) }
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

    private fun showError(msg: String) {
        hideLoading()
        binding.layoutMainSearchShowError.root.visibility = View.VISIBLE
        binding.layoutMainSearchShowError.txtMainError.text = msg
        binding.progressBar.visibility = View.GONE
        binding.rvData.visibility = View.GONE
    }

    private fun showData(data: List<ReposAndUsersData>) {
        hideLoading()
        Log.d(frTag, data.toString())
        setDataToAdapter(data)
    }

    private fun showLoading() {
        setFocusableOff()
        binding.layoutMainSearchShowError.root.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.rvData.visibility = View.GONE
    }

    private fun hideLoading() {
        setFocusableOn()
        binding.layoutMainSearchShowError.root.visibility = View.GONE
        binding.rvData.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun initAdapter() {
        adapter = DataAdapter(this)
        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvData.adapter = adapter
    }

    private fun setDataToAdapter(data: List<ReposAndUsersData>) =
        adapter.setData(data)

}