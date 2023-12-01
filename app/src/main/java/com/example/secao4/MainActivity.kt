package com.example.secao4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)


        data class Movie(val title: String, val director: String)

        val movieList = listOf(
            Movie("Alien", "Ridley Scott"),
            Movie("La La Land", "Damien Chazelle"),
            Movie("Nomadland", "Chloe Zhao"),
        )



        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(movie: Movie) {
                itemView.findViewById<TextView>(R.id.movieTitle).text = movie.title
                itemView.findViewById<TextView>(R.id.movieDirector).text = movie.director
            }
        }

        class MovieAdapter(private val movies: List<Movie>) :
            RecyclerView.Adapter<MovieViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                return MovieViewHolder(view)
            }

            override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
                val movie = movies[position]
                holder.bind(movie)
            }

            override fun getItemCount(): Int {
                return movies.size
            }

        }

        val adapter = MovieAdapter(movieList)

        recyclerView.adapter = adapter


    }
}