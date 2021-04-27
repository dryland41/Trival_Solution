package com.namespacermcw.api_practice;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeApiService {
    /**
     * API used: https://github.com/15Dkatz/official_joke_api
     */

    /* https://official-joke-api.appspot.com/random_joke */
    @GET("random_joke")
    Call<Joke> getRandomJoke();

    /* https://official-joke-api.appspot.com/random_ten */
    @GET("random_ten")
    Call<List<Joke>> getTenRandomJokes();
}
