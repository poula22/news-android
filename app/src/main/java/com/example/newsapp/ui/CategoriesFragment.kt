package com.example.newsapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class CategoriesFragment:Fragment() {
    private val categories= listOf<Category>(
        Category("sports",R.drawable.sports,R.string.sports,R.color.red),
        Category("business",R.drawable.bussines,R.string.business,R.color.brown),
        Category("entertainment",R.drawable.environment,R.string.entertainment,R.color.blue),
        Category("health",R.drawable.health,R.string.health,R.color.pink),
        Category("science",R.drawable.science,R.string.science,R.color.yellow),
        Category("technology",R.drawable.technology,R.string.technology,R.color.darkBlue)
    )
    lateinit var recyclerView: RecyclerView
    val adapter= CategoriesAdapter(categories)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView=requireView().findViewById(R.id.recycler_categories)
        recyclerView.adapter=adapter
        adapter.onItemClickLisener=object : CategoriesAdapter.OnItemClickLisener {
            override fun onItemClicked(pos: Int, item: Category) {
                onCategoryClickLisener?.onCategoryClicked(item)
            }
        }


        }
    var onCategoryClickLisener:OnCategoryClickLisener?=null
    interface OnCategoryClickLisener{
        fun onCategoryClicked(category: Category)
    }
    }
