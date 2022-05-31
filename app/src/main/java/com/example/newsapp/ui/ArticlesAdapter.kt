package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.api.module.ArticlesItem

class ArticlesAdapter(var items:List<ArticlesItem?>?=null):RecyclerView.Adapter<ArticlesAdapter.ViewHolder> (){
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var image: ImageView =view.findViewById(R.id.image)
        var title:TextView=view.findViewById(R.id.title)
        var author:TextView=view.findViewById(R.id.author)
        var dateTime:TextView=view.findViewById(R.id.datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_articles,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article= items!![position]
        holder.title.text=article?.title
        holder.author.text=article?.author
        holder.dateTime.text=article?.publishedAt
        Glide.with(holder.itemView).load(article?.urlToImage).into(holder.image)
    }

    override fun getItemCount(): Int =items?.size ?:0
    fun changeData(articles:List<ArticlesItem?>?){
        items=articles
        notifyDataSetChanged()
    }

}