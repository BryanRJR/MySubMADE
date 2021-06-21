package com.dicoding.picodiploma.mysubmade.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mysubmade.core.ui.MovieAdapter
import com.dicoding.picodiploma.mysubmade.databinding.ActivityFavoriteBinding
import com.dicoding.picodiploma.mysubmade.detail.DetailActivity
import com.dicoding.picodiploma.mysubmade.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteBinding: ActivityFavoriteBinding
    private val viewModelFavorite: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        loadKoinModules(favoriteModule)

        favoriteBinding.viewToolbar.setNavigationOnClickListener { onBackPressed() }

        val favAdapter = MovieAdapter()
        favAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }
        }

        viewModelFavorite.favoriteMovie.observe(this, {
        favAdapter.setData(it)
        favoriteBinding.viewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        favoriteBinding.recyclerMovie.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }
}