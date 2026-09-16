package com.example.dogcatbinaliktad

data class DogResponse(
    val message: String,
    val status: String
)

data class CatResponse(
    val id: String,
    val url: String,
    val width: Int?,
    val height: Int?
)