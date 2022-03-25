package com.example.news.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.LayoutNewsItemBinding
import com.example.news.model.Article
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val list: ArrayList<Article> = arrayListOf(),
    private val listener: NewsListListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: ArrayList<Article>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context)), listener)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = if (list.isNullOrEmpty()) 0 else list.size

    inner class NewsViewHolder(
        private val binding: LayoutNewsItemBinding,
        private val listener: NewsListListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.parent.setOnClickListener { listener.onItemClickListener(adapterPosition) }
        }

        fun bind(article: Article) {
            binding.txtTitle.text = article.title
            Picasso.get().load(article.url).into(binding.img)
        }
    }

    interface NewsListListener {
        fun onItemClickListener(pos: Int)
    }
}