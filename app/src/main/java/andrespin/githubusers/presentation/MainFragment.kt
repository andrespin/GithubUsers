package andrespin.githubusers.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import andrespin.githubusers.R
import andrespin.githubusers.base.BaseFragment
import andrespin.githubusers.databinding.FragmentMainBinding
import kotlinx.coroutines.Job

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {


    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override val frTag: String
        get() = "MainFragment"

    override fun initClickListeners() {

    }

    override fun observeViewModel(): Job {
        TODO("Not yet implemented")
    }


}