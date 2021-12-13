package me.aprilian.mynews.ui.news.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.view.BaseFragment
import me.aprilian.mynews.core.view.ItemDecoration
import me.aprilian.mynews.databinding.FragmentNewsCategoryBinding
import me.aprilian.mynews.datasource.api.response.toDomainSource

@AndroidEntryPoint
class NewsCategoryFragment : BaseFragment() {

    private val viewModel: NewsCategoryViewModel by viewModels()
    private val args: NewsCategoryFragmentArgs by navArgs()
    private var _binding: FragmentNewsCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var sourceAdapter: SourceAdapter
    private var count = 0

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
        //setupListener() disable this cause the sources API didn't support pagination
    }

    private fun setupListener() {
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                count++
                if (count < 20) {
                    // on below line we are making our progress bar visible.
                    binding.progressBar.visibility = View.VISIBLE

                    // on below line we are again calling
                    // a method to load data in our array list.
                    viewModel.loadAllSource()
                }
            }
        })
    }

    private fun setupArguments() {
        binding.tvTitle.text = args.category.title
        viewModel.setSourceTag(args.category.tag)
        viewModel.loadAllSource()
    }

    private fun setupObservers() {
        viewModel.sources.observe(viewLifecycleOwner, { result ->
            when(result.status){
                Resource.Status.LOADING -> { }
                Resource.Status.SUCCESS -> {
                    sourceAdapter.addData(
                        Resource.success(result.data?.sources?.map { it.toDomainSource() } ?: arrayListOf())
                    )
                }
                else -> {
                    toast(result.message)
                }
            }
        })
    }

    private fun setupAdapter() {
        sourceAdapter = SourceAdapter(requireContext(), Resource.loading()) { source ->
            openSourceNews(source?.id)
        }

        binding.rvSource.apply {
            adapter = sourceAdapter
            addItemDecoration(ItemDecoration(requireContext()))
        }
    }

    private fun openSourceNews(sourceId: String?){
        if (sourceId == null) return
        navigate(NewsCategoryFragmentDirections.openSource(sourceId))
    }

}