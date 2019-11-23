package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import org.unicef.parenthood.databinding.FragmentArticleBinding

/**
 * Shows full article content
 */
class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
    }
}
