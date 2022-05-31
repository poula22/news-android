package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.module.ArticleResponse
import com.example.newsapp.api.module.SourcesItem
import com.example.newsapp.api.module.SourcesResponse
import com.example.newsapp.constans.ConstantParameters
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment:Fragment() {

    companion object{
        fun getInstance(category: Category):NewsFragment{
            val fragment=NewsFragment()
            fragment.category=category
            return fragment;
        }
    }
    lateinit var category: Category
    lateinit var progressBar:ProgressBar
    lateinit var tabLayout: TabLayout
    lateinit var articlesRecyclerView: RecyclerView
    lateinit var adapter: ArticlesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getNewsSources(category)
    }

    private fun initViews() {
        progressBar=requireView().findViewById(R.id.progress_bar)

        articlesRecyclerView=requireView().findViewById(R.id.recycler_view_articles)
        adapter=ArticlesAdapter()
        articlesRecyclerView.adapter=adapter

        tabLayout=requireView().findViewById(R.id.tab_layout)
        tabLayout.addOnTabSelectedListener(
            object :TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val sources:SourcesItem=tab?.tag as SourcesItem
                    loadNews(sources)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val sources:SourcesItem=tab?.tag as SourcesItem
                    loadNews(sources)
                }


            }
        )

    }

    private fun getNewsSources(category: Category){
         ApiManager.getApis().getNewsSources(ConstantParameters.APIKEY,category.name)
            .enqueue(object:Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible=false
                    showTabs(response.body()?.sources)
                    Log.e("response",response.body().toString())
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    progressBar.isVisible=false
                }

            })
    }

    private fun showTabs(sources: List<SourcesItem?>?) {
        sources?.forEach{
            var tab=tabLayout.newTab()
            tab.text = it?.id
            tab.tag = it
            tabLayout.addTab(tab)
        }

    }
    private fun loadNews(sources:SourcesItem) {
        adapter.changeData(null)
        ApiManager.getApis().getNews(ConstantParameters.APIKEY, sources.id ?: "")
            .enqueue(object : Callback<ArticleResponse> {
                override fun onResponse(
                    call: Call<ArticleResponse>,
                    response: Response<ArticleResponse>
                ) {
                    adapter.changeData(response.body()?.articles)
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    Log.e("error","fail")
                }

            })
    }
}