package andrespin.githubusers.presentation.main

import andrespin.githubusers.databinding.FragmentMainBinding
import andrespin.githubusers.domain.entity.ReposAndUsersData
import andrespin.githubusers.presentation.base.BaseFragment
import andrespin.githubusers.presentation.main.adapter.DataAdapter
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.android.material.internal.ViewUtils.showKeyboard
import kotlinx.coroutines.launch

abstract class MainFragmentAbstract<VB : FragmentMainBinding, VM : MainViewModel> :
    BaseFragment<VB, VM>() {

    private lateinit var adapter: DataAdapter

    protected fun initAll(fragment: MainFragment) {
        initAdapter(fragment)
        initClickListener()
        setFocusableOn()
    }

    protected fun downloadEditTextRequest() = emitGetDataIntent(getEditTextStr())

    protected fun showError(msg: String) {
        hideLoading()
        hideAll()
        setErrorVisible(msg)
    }

    protected fun showData(data: List<ReposAndUsersData>) {
        hideLoading()
        setDataToAdapter(data)
        showAdapter()
    }

    private fun showAdapter() {
        hideAll()
        setAdapterVisible()
    }

    private fun setAdapterVisible() {
        binding.rvData.visibility = View.VISIBLE
    }

    private fun getEditTextStr() = binding.layoutSearchView.editUser.text.toString()

    private fun emitGetDataIntent(req: String) =
        lifecycleScope.launch { model.intent.emit(MainIntent.GetData(req)) }

    private fun initAdapter(fragment: MainFragment) {
        adapter = DataAdapter(fragment)
        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvData.adapter = adapter
    }


    private fun initClickListener() {
        initTryAgainClickListener()
        initSearchClickListener()
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

    private fun setFocusableOn() {
        binding.layoutSearchView.editUserNotActive.visibility = View.GONE
        binding.layoutSearchView.editUser.visibility = View.VISIBLE
    }

    private fun setFocusableOff() {
        binding.layoutSearchView.editUserNotActive.visibility = View.VISIBLE
        binding.layoutSearchView.editUser.visibility = View.GONE
        binding.layoutSearchView.editUserNotActive.text = binding.layoutSearchView.editUser.text
    }

    private fun hideAll() {
        binding.layoutMainSearchShowError.root.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.rvData.visibility = View.GONE
    }

    protected fun showLoading() {
        setFocusableOff()
        hideAll()
        setLoadingVisible()
    }

    private fun setLoadingVisible() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setLoadingGone() {
        binding.progressBar.visibility = View.GONE
    }

    private fun hideLoading() {
        setFocusableOn()
        setLoadingGone()
    }

    private fun setDataToAdapter(data: List<ReposAndUsersData>) =
        adapter.setData(data)

    private fun setErrorVisible(msg: String) {
        binding.layoutMainSearchShowError.root.visibility = View.VISIBLE
        binding.layoutMainSearchShowError.txtMainError.text = msg
    }

}