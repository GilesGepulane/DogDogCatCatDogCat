package com.example.dogcatbinaliktad

import retrofit2.http.GET

interface DogApiService {

    @GET("breeds/image/random")
    suspend fun getRandomDog(): DogResponse
}

interface CatApiService {

    @GET("images/search")
    suspend fun getRandomCat(): List<CatResponse>
}