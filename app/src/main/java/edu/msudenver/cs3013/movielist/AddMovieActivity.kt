package edu.msudenver.cs3013.movielist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddMovieActivity : AppCompatActivity() {

    private lateinit var titleEdit: EditText
    private lateinit var yearEdit: EditText
    private lateinit var genreEdit: EditText
    private lateinit var ratingEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        titleEdit = findViewById(R.id.titleEdit)
        yearEdit = findViewById(R.id.yearEdit)
        genreEdit = findViewById(R.id.genreEdit)
        ratingEdit = findViewById(R.id.ratingEdit)

        findViewById<View>(R.id.saveButton).setOnClickListener { saveAndExit() }
    }

    fun saveAndExit() {
        val out = Intent().apply {
            putExtra("title", titleEdit.text.toString())
            putExtra("year", yearEdit.text.toString())
            putExtra("genre", genreEdit.text.toString())
            putExtra("rating", ratingEdit.text.toString())
        }
        setResult(RESULT_OK, out)
        finish()
    }
}