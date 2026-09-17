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

class dogvvdog : AppCompatActivity() {

    private lateinit var statusText: TextView

    private val repository = AnimalRepo()

    private lateinit var dogImage1: ImageView
    private lateinit var dogImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dogvdog)

        dogImage1 = findViewById(R.id.dogImage1)
        dogImage2 = findViewById(R.id.dogImage2)
        statusText = findViewById(R.id.statusText)

        val replaceDog1: Button = findViewById(R.id.replaceDog1)
        val replaceDog2: Button = findViewById(R.id.replaceDog2)
        val backToMenu: Button = findViewById(R.id.BacktoMenu)

        loadAllImages()

        replaceDog1.setOnClickListener {
            loadDogImage(dogImage1)
        }

        replaceDog2.setOnClickListener {
            loadDogImage(dogImage2)
        }

        backToMenu.setOnClickListener {
            finish()
        }
    }

    private fun loadAllImages() {
        lifecycleScope.launch {

            statusText.text = "Loading images..."

            val requests = listOf(
                async {
                    loadDogImage(dogImage1, false)
                },
                async {
                    loadDogImage(dogImage2, false)
                }
            )
        }
    }

    private fun loadDogImage(
        imageView: ImageView,
        showStatus: Boolean = true
    ) {
        lifecycleScope.launch {

            try {

                if (showStatus) {
                    statusText.text = "Loading new dog..."
                }

                val imageUrl = repository.getDogImage()

                imageView.load(imageUrl)

                if (showStatus) {
                    statusText.text = "Dog replaced!"
                }

            } catch (e: Exception) {

                statusText.text =
                    "Failed to load dog image.\nPlease check your internet connection."

            }
        }
    }
}