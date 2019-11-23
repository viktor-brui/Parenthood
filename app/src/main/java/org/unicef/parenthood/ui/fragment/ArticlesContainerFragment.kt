package org.unicef.parenthood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.unicef.parenthood.databinding.FragmentArticlesContainerBinding

class ArticlesContainerFragment : Fragment() {

    private lateinit var binding: FragmentArticlesContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return inflater.inflate(R.layout.fragment_articles_container, container, false)
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
