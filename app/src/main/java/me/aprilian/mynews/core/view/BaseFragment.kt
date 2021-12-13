package me.aprilian.mynews.core.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import me.aprilian.mynews.core.utils.toast
import androidx.navigation.*

open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressListener()
    }

    private fun onBackPressListener(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                navigateBack()
            }
        })
    }

    open fun navigateBack() {
        view?.findNavController()?.navigateUp()
    }

    open fun navigate(directions: NavDirections) {
        view?.findNavController()?.navigate(directions)
    }

    fun toast(message: String?, duration: Int = Toast.LENGTH_SHORT){
        context?.toast(message, duration)
    }

}