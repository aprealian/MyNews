package me.aprilian.mynews.ui.news.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import me.aprilian.mynews.core.data.Resource
import me.aprilian.mynews.core.view.BaseFragment
import me.aprilian.mynews.core.view.ItemDecoration
import me.aprilian.mynews.databinding.FragmentSourceBinding

class SourceFragment : BaseFragment() {

    private val viewModel: SourceViewModel by viewModels()
    private val args: SourceFragmentArgs by navArgs()
    private var _binding: FragmentSourceBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter

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
    }

    private fun setupArguments() {
        viewModel.setSourceTag(args.sourceId)
    }

    private fun setupObservers() {
        viewModel.articles.observe(viewLifecycleOwner, {
            articleAdapter.submitData(it)
        })
    }

    private fun setupAdapter() {
        articleAdapter = ArticleAdapter(requireContext(), Resource.loading()) { source ->
            //toast(source?.title)
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

}