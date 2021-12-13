package me.aprilian.mynews.ui.news.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.view.BaseFragment
import me.aprilian.mynews.core.view.ItemDecoration
import me.aprilian.mynews.databinding.FragmentSourceBinding
import me.aprilian.mynews.datasource.api.response.toDomainArticle
import androidx.core.widget.NestedScrollView
import me.aprilian.mynews.core.utils.gone

@AndroidEntryPoint
class SourceFragment : BaseFragment() {

    private val viewModel: SourceViewModel by viewModels()
    private val args: SourceFragmentArgs by navArgs()
    private var _binding: FragmentSourceBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter
    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSourceBinding.inflate(inflater).also {
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
        setupListener()
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
                    viewModel.loadAllNews()
                }
            }
        })
    }

    private fun setupArguments() {
        viewModel.setSourceTag(args.sourceId)
        viewModel.loadAllNews()
    }

    private fun setupObservers() {
        viewModel.articles.observe(viewLifecycleOwner, { result ->
            when(result.status){
                Resource.Status.LOADING -> { }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    articleAdapter.addData(
                        Resource.success(result.data?.articles?.map { it.toDomainArticle() } ?: arrayListOf())
                    )
                }
                else -> {
                    binding.progressBar.gone()
                    articleAdapter.errorState(message = "ERROR: "+result.message)
                }
            }
        })
    }

    private fun setupAdapter() {
        articleAdapter = ArticleAdapter(requireContext(), Resource.loading()) { source ->
            openArticle(source?.url)
        }

        binding.rvArticles.apply {
            adapter = articleAdapter
            addItemDecoration(ItemDecoration(requireContext()))
        }
    }

    private fun openArticle(url: String?){
        if (url == null) return
        navigate(SourceFragmentDirections.openArticle(url))
    }

    override fun navigateBack() {
        navigate(SourceFragmentDirections.fromSourceBackToCategory())
    }

}