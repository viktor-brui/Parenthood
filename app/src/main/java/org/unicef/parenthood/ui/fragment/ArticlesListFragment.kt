package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import org.unicef.parenthood.adapter.ArticlesAdapter
import org.unicef.parenthood.databinding.FragmentArticlesListBinding
import org.unicef.parenthood.ui.viewmodel.ArticlesContainerViewModel

class ArticlesListFragment : Fragment() {

    private val viewModel: ArticlesContainerViewModel by viewModels(ownerProducer = { requireActivity() })
    private lateinit var binding: FragmentArticlesListBinding

    private val tabPosition by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getInt(ARG_TAB_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticlesAdapter { position ->
            // TODO(): navigation
        }
        binding.articlesList.adapter = adapter
        val decoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        binding.articlesList.addItemDecoration(decoration)

        viewModel.getArticlesById(position = tabPosition).observe(viewLifecycleOwner) { articles ->
            adapter.setArticles(articles)
        }
    }

    companion object {
        private const val ARG_TAB_ID = "ARG_TAB_ID"

        fun newInstance(tabFragmentId: Int): ArticlesListFragment {
            return ArticlesListFragment().apply {
                arguments = bundleOf(ARG_TAB_ID to tabFragmentId)
            }
        }
    }
}
