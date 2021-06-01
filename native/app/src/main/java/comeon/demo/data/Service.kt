package comeon.demo.data

import comeon.demo.data.games.Games
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("games.json")
    suspend fun games(): Response<List<Games>>
}