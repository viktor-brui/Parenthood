package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import coil.api.load
import org.unicef.parenthood.R
import org.unicef.parenthood.databinding.FragmentArticleBinding
import org.unicef.parenthood.ui.viewmodel.ArticleViewModel
import org.unicef.parenthood.ui.viewmodel.ArticlesContainerViewModel
import org.unicef.parenthood.util.observeEvent

/**
 * Shows full article content
 */
class ArticleFragment : Fragment() {

    private val args: ArticleFragmentArgs by navArgs()

    private val articlesContainerViewModel: ArticlesContainerViewModel by navGraphViewModels(R.id.articles_nav_graph)
    private val viewModel: ArticleViewModel by viewModels()

    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.article = args.article
        binding.tvCategory.text = args.article.categories.joinToString(", ")
        if (!args.article.mainImage.isNullOrEmpty()) {
            binding.imgMain.load(args.article.mainImage)
        } else {
            binding.imgMain.isVisible = false
        }

        if (args.article.testId.isNullOrEmpty()) {
            binding.btnTest.isVisible = false
            binding.btnCreateTest.setOnClickListener {
                val direction = ArticleFragmentDirections
                    .actionArticleFragmentToCreateTestFragment(requireNotNull(args.article.id))
                findNavController().navigate(direction)
            }
        } else {
            binding.btnCreateTest.isVisible = false
            binding.btnTest.setOnClickListener {
                val direction = ArticleFragmentDirections
                    .actionArticleFragmentToTakeTestFragment(requireNotNull(args.article.testId))
                findNavController().navigate(direction)
            }
        }

        if (args.upload) {
            viewModel.uploadArticle(args.article)
        }

        viewModel.onArticleUploaded.observeEvent(viewLifecycleOwner) {
            articlesContainerViewModel.loadArticles()
        }
    }
}
