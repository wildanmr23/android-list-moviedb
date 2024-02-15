package com.example.movielist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.movielist.adapter.MovieAdapter
import com.example.movielist.model.MoviesLIst
import com.google.gson.Gson
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    companion object {
        private const val apiKey = "f8bdd5650dbf44478466bcba81d7c20a"
    }

    // variabel global untuk menghubungkan kotlin dengan xml yang dituju
    private lateinit var rvMovie: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovie = findViewById(R.id.rvMovie)

        getDataMovie()
    }

    private fun getDataMovie() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$apiKey"

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
// Untuk cek keberadaan data
//            Log.d("WIldan", "getDataMovie: $response")
            val movieResponse = Gson().fromJson(response, MoviesLIst::class.java)
            if (movieResponse != null) {
//                Log.d("WIldan", "getDataMovie: ${movieResponse.totalPages}, ${movieResponse.results?.size}")
                initMovieAdapter(movieResponse)
            }
        }, { error ->

        })
        queue.add(stringRequest)
    }

    private fun initMovieAdapter(movieResponse: MoviesLIst) {
        val adapter = movieResponse.results?.let { MovieAdapter(this, it) }
        adapter?.notifyDataSetChanged()

        rvMovie.layoutManager = LinearLayoutManager(this)
        rvMovie.adapter = adapter
    }


}