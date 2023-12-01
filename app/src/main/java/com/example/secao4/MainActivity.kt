package com.example.secao4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), IClickEventListener {

    data class Movie(val title: String, val director: String)

    private val movieList = listOf(
        Movie("Alien", "Ridley Scott"),
        Movie("La La Land", "Damien Chazelle"),
        Movie("Nomadland", "Chloe Zhao"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)


        class MovieViewHolder(itemView: View, private val clickListener: IClickEventListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(p0: View?){


                val position = adapterPosition

                if(position != RecyclerView.NO_POSITION){
                    clickListener.onItemClick(position)
                }
            }
            fun bind(movie: Movie) {
                itemView.findViewById<TextView>(R.id.movieTitle).text = movie.title
                itemView.findViewById<TextView>(R.id.movieDirector).text = movie.director
            }
        }

        class MovieAdapter(private val movies: List<Movie>, private val clickListener: IClickEventListener) :
            RecyclerView.Adapter<MovieViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
                return MovieViewHolder(view, clickListener)
            }

            override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
                val movie = movies[position]
                holder.bind(movie)
            }

            override fun getItemCount(): Int {
                return movies.size
            }

        }

        val adapter = MovieAdapter(movieList, this)

        recyclerView.adapter = adapter
    }
    override fun onItemClick(position: Int) {
        val movie = movieList[position]
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()

    }
}