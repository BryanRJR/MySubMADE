package com.dicoding.picodiploma.mysubmade

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.mysubmade.core.ui.MovieAdapter
import com.dicoding.picodiploma.mysubmade.databinding.ActivityMainBinding
import com.dicoding.picodiploma.mysubmade.detail.DetailActivity
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModelMovies: MovieViewModel by viewModel()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var moviesAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        btn_theme.addOnButtonCheckedListener(object : MaterialButtonToggleGroup.OnButtonCheckedListener {
            override fun onButtonChecked(group: MaterialButtonToggleGroup?, selectedBtnId: Int, isChecked: Boolean) {
                val theme = when (selectedBtnId) {
                    R.id.btnDark -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(theme)
            }
        })

        moviesAdapter = MovieAdapter()
        moviesAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }

        }

        viewModelMovies.movie.observe(this, {
            if (it != null) {
                when (it) {
                    is com.dicoding.picodiploma.mysubmade.core.source.Resource.Loading -> true.progressbar()
                    is com.dicoding.picodiploma.mysubmade.core.source.Resource.Success -> {
                        false.progressbar()
                        moviesAdapter.setData(it.data)
                    }
                    is com.dicoding.picodiploma.mysubmade.core.source.Resource.Error -> {
                        false.progressbar()
                        mainBinding.viewError.apply {
                            root.visibility = View.INVISIBLE
                            tvError.text = it.message
                        }

                    }
                }
            }
        })

        setRecyclerView()

        val uri = Uri.parse("mysubmade://favorite")
        mainBinding.fab.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setRecyclerView() {
        with(mainBinding.recyclerMovie) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }


    private fun Boolean.progressbar() {
        mainBinding.progressBar.visibility = if (this) View.VISIBLE else View.INVISIBLE
    }


}