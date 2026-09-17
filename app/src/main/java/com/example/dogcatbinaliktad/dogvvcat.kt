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
import androidx.lifecycle.lifecycleScope
import coil.EventListener
import coil.load
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import com.example.dogcatbinaliktad.AnimalRepo
class dogvvcat : AppCompatActivity() {


    private lateinit var statusText: TextView

    private val repository = AnimalRepo()
    private lateinit var mixedCatImage: ImageView
    private lateinit var mixedDogImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.catvdog)

        mixedCatImage = findViewById(R.id.mixedCatImage)
        mixedDogImage = findViewById(R.id.mixedDogImage)
        statusText = findViewById(R.id.statusText)

        val replaceMixedCat: Button = findViewById(R.id.replaceMixedCat)
        val replaceMixedDog: Button = findViewById(R.id.replaceMixedDog)
        val backToMenu: Button = findViewById(R.id.BacktoMenu)

        loadAllImages()

        replaceMixedCat.setOnClickListener {
            loadCatImage(mixedCatImage)
        }

        replaceMixedDog.setOnClickListener {
            loadDogImage(mixedDogImage)
        }

        backToMenu.setOnClickListener {
            finish()
        }
    }

    private fun loadAllImages() {
        lifecycleScope.launch {
            statusText.text = "Loading images..."

            val requests = listOf(
                async { loadCatImage(mixedCatImage, false) },
                async { loadDogImage(mixedDogImage, false) }
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