package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.unicef.parenthood.R
import org.unicef.parenthood.databinding.FragmentArticlesContainerBinding
import org.unicef.parenthood.ui.viewmodel.ArticlesContainerViewModel
import org.unicef.parenthood.util.observeEvent

class ArticlesContainerFragment : Fragment() {

    private val viewModel: ArticlesContainerViewModel by navGraphViewModels(R.id.articles_nav_graph)

    private lateinit var binding: FragmentArticlesContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpager.adapter = ViewPagerFragmentStateAdapter(requireActivity())
        TabLayoutMediator(
            binding.tabview,
            binding.viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "Editor's Pick"
                    1 -> "All"
                    else -> "All"
                }
            }
        ).attach()

        viewModel.onArticleClicked.observeEvent(viewLifecycleOwner) { (article, upload) ->
            val direction =
                ArticlesContainerFragmentDirections.actionArticlesContainerFragmentToArticleFragment(
                    article = article,
                    upload = upload
                )
            findNavController().navigate(direction)
        }
    }

    class ViewPagerFragmentStateAdapter(
        activity: FragmentActivity
    ) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return ArticlesListFragment.newInstance(position)
        }
    }
}
