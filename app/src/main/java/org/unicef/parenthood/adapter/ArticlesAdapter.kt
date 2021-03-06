package org.unicef.parenthood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import org.unicef.parenthood.databinding.ArticleRowBinding
import org.unicef.parenthood.repository.model.ArticleEntity

class ArticlesAdapter(
    private val clickListener: (article: ArticleEntity) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    private val articles: MutableList<ArticleEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleRowBinding
            .inflate(inflater, parent, false)
        return ViewHolder(
            binding,
            clickListener
        )
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun setArticles(newArticles: List<ArticleEntity>) {
        articles.clear()
        articles += newArticles
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ArticleRowBinding,
        listener: (article: ArticleEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener(requireNotNull(binding.article))
            }
        }

        fun bind(article: ArticleEntity) {
            binding.article = article
            binding.categories.text = article.categories.joinToString(", ")
            binding.articleImage.load(article.mainImage)
            binding.executePendingBindings()
        }
    }
}