package com.dicoding.picodiploma.mysubmade.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubmade.core.R
import com.dicoding.picodiploma.mysubmade.core.databinding.ItemListMovieBinding
import com.dicoding.picodiploma.mysubmade.core.model.Movie
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listMovie.clear()
        listMovie.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.coverMovie)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loadingscreen)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageMovie)
                textTitleMovie.text = movie.title
                textDate.text = movie.date
                textDescription.text = movie.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }


    override fun getItemCount(): Int = listMovie.size
}