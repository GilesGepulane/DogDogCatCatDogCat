package com.example.dogcatbinaliktad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.EventListener
import coil.load
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import com.example.dogcatbinaliktad.AnimalRepo

class MainActivity : AppCompatActivity() {

    private lateinit var dogImage1: ImageView
    private lateinit var dogImage2: ImageView
    private lateinit var catImage1: ImageView
    private lateinit var catImage2: ImageView
    private lateinit var mixedCatImage: ImageView
    private lateinit var mixedDogImage: ImageView
    private lateinit var statusText: TextView

    private val repository = AnimalRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val btnDogVsDog: Button = findViewById(R.id.button2)
        val btnCatVsCat: Button = findViewById(R.id.button3)
        val btnDogVsCat: Button = findViewById(R.id.button4)

        btnDogVsDog.setOnClickListener {
            startActivity(Intent(this, dogvvdog::class.java))
        }
        btnDogVsCat.setOnClickListener {
            startActivity(Intent(this, dogvvcat::class.java))
        }
        btnCatVsCat.setOnClickListener {
            startActivity(Intent(this, catvvcat::class.java))
        }

    }
}