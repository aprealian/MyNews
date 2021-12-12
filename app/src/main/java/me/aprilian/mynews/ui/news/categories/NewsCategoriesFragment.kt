package me.aprilian.mynews.ui.news.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.view.BaseFragment
import me.aprilian.mynews.core.view.ItemDecoration
import me.aprilian.mynews.databinding.FragmentNewsCategoriesBinding
import me.aprilian.mynews.domain.Category

class NewsCategoriesFragment : BaseFragment() {

    private val viewModel: NewsCategoriesViewModel by viewModels()
    private var _binding: FragmentNewsCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsCategoriesAdapter: NewsCategoriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsCategoriesBinding.inflate(inflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.fragment = this
            it.viewModel = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.categories.observe(viewLifecycleOwner, {
            newsCategoriesAdapter.submitData(it)
        })
    }

    private fun setupAdapter() {
        newsCategoriesAdapter = NewsCategoriesAdapter(requireContext(), Resource.loading()) { category ->
            //toast(category?.title)
            openCategory(category)
        }

        binding.rvCategories.apply {
            adapter = newsCategoriesAdapter
            addItemDecoration(ItemDecoration(requireContext()))
        }
    }

    private fun openCategory(category: Category?){
        if (category == null) return toast("Category not found")
        navigate(NewsCategoriesFragmentDirections.openCategory(category))
    }

}