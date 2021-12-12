package me.aprilian.mynews.ui.news.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import me.aprilian.mynews.core.view.BaseFragment
import me.aprilian.mynews.databinding.FragmentArticleBinding

class ArticleFragment : BaseFragment() {

    private val args: ArticleFragmentArgs by navArgs()
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentArticleBinding.inflate(inflater).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.fragment = this
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArguments()
    }

    private fun setupArguments() {
        openNewsWebsite(args.sourceUrl)
    }

    private fun openNewsWebsite(url: String?){
        if (url.isNullOrEmpty()) return toast("URL not found")

        binding.tvUrl.text = url
        binding.webView.loadUrl(url)
    }

}