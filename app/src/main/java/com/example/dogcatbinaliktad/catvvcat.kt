package com.example.dogcatbinaliktad

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class catvvcat : AppCompatActivity() {


    private lateinit var statusText: TextView

    private val repository = AnimalRepo()
    private lateinit var catImage1: ImageView
    private lateinit var catImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.catvcat)

        catImage1 = findViewById(R.id.catImage1)
        catImage2 = findViewById(R.id.catImage2)
        statusText = findViewById(R.id.statusText)

        val replaceCat1: Button = findViewById(R.id.replaceCat1)
        val replaceCat2: Button = findViewById(R.id.replaceCat2)
        val backToMenu: Button = findViewById(R.id.BacktoMenu)

        loadAllImages()

        replaceCat1.setOnClickListener {
            loadCatImage(catImage1)
        }

        replaceCat2.setOnClickListener {
            loadCatImage(catImage2)
        }

        backToMenu.setOnClickListener {
            finish()
        }
    }

    private fun loadAllImages() {
        lifecycleScope.launch {
            statusText.text = "Loading images..."

            val requests = listOf(
                async { loadCatImage(catImage1, false) },
                async { loadCatImage(catImage2, false) },
            )
        }
    }

    private fun loadCatImage(
        imageView: ImageView,
        showStatus: Boolean = true
    ) {
        lifecycleScope.launch {
            try {
                if (showStatus) {
                    statusText.text = "Loading new cat..."
                }

                val imageUrl = repository.getCatImage()
                imageView.load(imageUrl)

                if (showStatus) {
                    statusText.text = "Cat replaced!"
                }

            } catch (e: Exception) {
                statusText.text = "Failed to load cat image. \n Please check your internet connection."
            }
        }
    }
}