package me.aprilian.mynews.ui.news.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.view.BaseFragment
import me.aprilian.mynews.core.view.ItemDecoration
import me.aprilian.mynews.databinding.FragmentNewsCategoryBinding

class NewsCategoryFragment : BaseFragment() {

    private val viewModel: NewsCategoryViewModel by viewModels()
    private val args: NewsCategoryFragmentArgs by navArgs()
    private var _binding: FragmentNewsCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var sourceAdapter: SourceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsCategoryBinding.inflate(inflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.fragment = this
            it.viewModel = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArguments()
        setupAdapter()
        setupObservers()
    }

    private fun setupArguments() {
        viewModel.setSourceTag(args.sourceId)
    }

    private fun setupObservers() {
        viewModel.sources.observe(viewLifecycleOwner, {
            sourceAdapter.submitData(it)
        })
    }

    private fun setupAdapter() {
        sourceAdapter = SourceAdapter(requireContext(), Resource.loading()) { source ->
            toast(source?.name)
        }

        binding.rvSource.apply {
            adapter = sourceAdapter
            addItemDecoration(ItemDecoration(requireContext()))
        }
    }

}