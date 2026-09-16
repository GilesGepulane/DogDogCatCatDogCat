package com.example.dogcatbinaliktad

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
        setContentView(R.layout.activity_main)

        // Connect Kotlin variables to XML views
        dogImage1 = findViewById(R.id.dogImage1)
        dogImage2 = findViewById(R.id.dogImage2)
        catImage1 = findViewById(R.id.catImage1)
        catImage2 = findViewById(R.id.catImage2)
        mixedCatImage = findViewById(R.id.mixedCatImage)
        mixedDogImage = findViewById(R.id.mixedDogImage)
        statusText = findViewById(R.id.statusText)

        val replaceDog1: Button = findViewById(R.id.replaceDog1)
        val replaceDog2: Button = findViewById(R.id.replaceDog2)
        val replaceCat1: Button = findViewById(R.id.replaceCat1)
        val replaceCat2: Button = findViewById(R.id.replaceCat2)
        val replaceMixedCat: Button = findViewById(R.id.replaceMixedCat)
        val replaceMixedDog: Button = findViewById(R.id.replaceMixedDog)

        // Load all six images when the app starts
        loadAllImages()

        // Replace only the selected image
        replaceDog1.setOnClickListener {
            loadDogImage(dogImage1)
        }

        replaceDog2.setOnClickListener {
            loadDogImage(dogImage2)
        }

        replaceCat1.setOnClickListener {
            loadCatImage(catImage1)
        }

        replaceCat2.setOnClickListener {
            loadCatImage(catImage2)
        }

        replaceMixedCat.setOnClickListener {
            loadCatImage(mixedCatImage)
        }

        replaceMixedDog.setOnClickListener {
            loadDogImage(mixedDogImage)
        }
    }

    private fun loadAllImages() {
        lifecycleScope.launch {
            statusText.text = "Loading images..."

            val requests = listOf(
                async { loadDogImage(dogImage1, false) },
                async { loadDogImage(dogImage2, false) },
                async { loadCatImage(catImage1, false) },
                async { loadCatImage(catImage2, false) },
                async { loadCatImage(mixedCatImage, false) },
                async { loadDogImage(mixedDogImage, false) }
            )

            requests.awaitAll()

            statusText.text = "All images loaded!"
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
                statusText.text = "Failed to load dog image. \n Please check your internet connection."
            }
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