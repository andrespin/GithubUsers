package andrespin.githubusers.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import andrespin.githubusers.base.BaseFragment
import andrespin.githubusers.databinding.FragmentMainBinding
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.presentation.main.adapter.repos_and_users.DataAdapter
import android.util.Log
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
        binding.layoutSearchView.imgSearch.setOnClickListener {
            Log.d(frTag, "Start")
            lifecycleScope.launch { model.intent.emit(MainIntent.GetData) }
        }

    }

    override fun observeViewModel() = lifecycleScope.launch {

        model.state.collectLatest {
            when (it) {
                MainState.Loading -> showLoading()
                is MainState.ShowData -> showData(it.data)
            }
        }

    }

    private fun showData(data: List<ReposAndUsersData>) {
        hideLoading()
        Log.d(frTag, data.toString())
        setDataToAdapter(data)
    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }

    private fun initAdapter() {
        adapter = DataAdapter(this)
        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvData.adapter = adapter
    }

    private fun setDataToAdapter(data: List<ReposAndUsersData>) =
        adapter.setData(data)

}