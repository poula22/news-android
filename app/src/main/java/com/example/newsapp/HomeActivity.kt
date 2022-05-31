package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.ui.CategoriesFragment
import com.example.newsapp.ui.Category
import com.example.newsapp.ui.NewsFragment

val categoryFragment=CategoriesFragment()
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        pushFragment(categoryFragment)
        categoryFragment.onCategoryClickLisener=object:CategoriesFragment.OnCategoryClickLisener{
            override fun onCategoryClicked(category: Category) {
                pushFragment(NewsFragment.getInstance(category),true)
            }

        }
    }

    private fun pushFragment(fragment: Fragment,addToBackStack:Boolean=false) {
        val fragmentTransaction=supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment)
        if (addToBackStack) fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }
}