package com.namespacermcw.api_practice;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TriviaRepository {
    //Base url must end with a / (slash)
    //Remember to use https:// if applicable
    private final String BASE_URL = "https://jservice.io/api/";

    //STEP 1: Created Retrofit object through a builder, set baseUrl and added GsonConverter
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //STEP 2: Created Retrofit Api Service Object holding relevant network calls
    TriviaApiService triviaService = retrofit.create(TriviaApiService.class);

    /**
     * Provides the call object for the list of random Joke api
     */
    public Call<List<Trivium>> getTenRandomQuestionsCall() { return triviaService.getRandomQuestions(10); }
}
