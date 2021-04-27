package com.namespacermcw.api_practice;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeRepository {
    //Base url must end with a / (slash)
    //Remember to use https:// if applicable
    private final String BASE_URL = "https://official-joke-api.appspot.com/";

    //STEP 1: Created Retrofit object through a builder, set baseUrl and added GsonConverter
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //STEP 2: Created Retrofit Api Service Object holding relevant network calls
    JokeApiService jokeService = retrofit.create(JokeApiService.class);

    /**
     * Provides the call object for the random Jokes api
     */
    public Call<Joke> getRandomJokeCall() { return jokeService.getRandomJoke(); }

    /**
     * Provides the call object for the list of random Joke api
     */
    public Call<List<Joke>> getTenRandomJokesCall() { return jokeService.getTenRandomJokes(); }
}
