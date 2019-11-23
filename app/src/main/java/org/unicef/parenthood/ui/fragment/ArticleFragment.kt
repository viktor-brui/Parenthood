package org.unicef.parenthood.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.unicef.parenthood.R

/**
 * Shows full article content
 */
class ArticleFragment : Fragment() {
//todo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    companion object {
        fun newInstance() = ArticleFragment()
    }
}
