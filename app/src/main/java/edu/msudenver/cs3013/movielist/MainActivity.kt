package edu.msudenver.cs3013.movielist

// Zachary Surlow
// 5/9/2025
// CS 3013
// Major Program 3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {
    private val movieList = mutableListOf<Movie>()
    private val movieAdapter = MovieAdapter(movieList)

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { res ->
        if (res.resultCode == RESULT_OK) {
            res.data?.let { data ->
                movieList.add(
                    Movie(
                        data.getStringExtra("title")!!,
                        data.getStringExtra("genre")!!,
                        data.getStringExtra("rating")!!,
                        data.getStringExtra("year")!!
                    )
                )
                movieAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = movieAdapter

        ItemTouchHelper(movieAdapter.swipeToDeleteCallback)
            .attachToRecyclerView(recyclerView)

        findViewById<View>(R.id.addMovieButton).setOnClickListener { startAdd() }
        findViewById<View>(R.id.saveListButton).setOnClickListener { writeFile() }

        readFile()
    }

    fun startAdd() {
        launcher.launch(Intent(this, AddMovieActivity::class.java))
    }

    private fun readFile() {
        try {
            openFileInput("MOVIELIST.csv").bufferedReader().useLines { lines ->
                lines.forEach { line ->
                    val parts = line.split(",")
                    if (parts.size == 4) {
                        movieList.add(Movie(parts[0], parts[1], parts[2], parts[3]))
                    }
                }
            }
        } catch (e: FileNotFoundException) {

        }
    }

    private fun writeFile() {
        openFileOutput("MOVIELIST.csv", MODE_PRIVATE).bufferedWriter().use { writer ->
            movieList.forEach { movie ->
                writer.write("${movie.title},${movie.year},${movie.genre},${movie.rating}\n")
            }
        }
    }
}