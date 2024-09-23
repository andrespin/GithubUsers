package andrespin.githubusers.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Job
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private lateinit var _viewBinding: VB

    protected lateinit var model: VM

    abstract val viewModelClass: Class<VM>

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    abstract val frTag: String

    protected val binding: VB
        get() = _viewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("$frTag lifecycle", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        Log.d("$frTag lifecycle", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.d("$frTag lifecycle", "onCreateView")
        _viewBinding = bindingInflater.invoke(inflater, container, false)
        return _viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeViewModel()
        process()
        Log.d("$frTag lifecycle", "onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("$frTag lifecycle", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("$frTag lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("$frTag lifecycle", "onResume")
    }


    override fun onPause() {
        super.onPause()
        pause()
        Log.d("$frTag lifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("$frTag lifecycle", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("$frTag lifecycle", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
        Log.d("$frTag lifecycle", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("$frTag lifecycle", "onDetach")
    }

    abstract fun init()

    open fun pause() {

    }

    open fun destroy() {}

    open fun process() {

    }

    abstract fun observeViewModel(): Job

    private fun initViewModel() {
        model = ViewModelProvider(this).get(viewModelClass)
    }

    fun toastMessage(text: String) {
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(requireContext(), text, duration)
        toast.show()
    }

}