package com.example.dogcatbinaliktad

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val dogApi: DogApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(DogApiService::class.java)
    }

    val catApi: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(CatApiService::class.java)
    }
}