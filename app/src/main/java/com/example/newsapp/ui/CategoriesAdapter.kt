package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class CategoriesAdapter(val categoriesList:List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title:TextView=view.findViewById(R.id.title)
        val image:ImageView=view.findViewById(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat=categoriesList[position]
        holder.title.setText(cat.titleId)
        holder.image.setImageResource(cat.imageId)
        holder.image.setBackgroundResource(cat.background)
        onItemClickLisener?.let {
            holder.itemView.setOnClickListener{
                onItemClickLisener?.onItemClicked(position,cat)
            }
        }
    }

    override fun getItemCount(): Int =categoriesList.size?:0

    var onItemClickLisener: OnItemClickLisener?=null
    interface OnItemClickLisener{
        fun onItemClicked(pos:Int,item: Category)
    }
}