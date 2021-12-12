package me.aprilian.mynews.core.view

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import me.aprilian.mynews.core.utils.toast

open class BaseFragment : Fragment() {

    open fun navigateBack() {
        view?.findNavController()?.navigateUp()
    }

    fun toast(message: String?, duration: Int = Toast.LENGTH_SHORT){
        context?.toast(message, duration)
    }
}