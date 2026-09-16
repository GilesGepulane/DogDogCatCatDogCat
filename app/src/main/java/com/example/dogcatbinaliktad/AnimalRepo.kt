package com.example.dogcatbinaliktad

class AnimalRepo {
    suspend fun getDogImage(): String {
        val response = ApiClient.dogApi.getRandomDog()

        if (response.status != "success" ||
            response.message.isBlank()
        ) {
            throw Exception("Invalid dog image response")
        }

        return response.message
    }

    suspend fun getCatImage(): String {
        val response = ApiClient.catApi.getRandomCat()

        if (response.isEmpty() ||
            response[0].url.isBlank()
        ) {
            throw Exception("No cat image found")
        }

        return response[0].url
    }
}