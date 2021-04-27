package com.namespacermcw.api_practice;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaApiService {
    /**
     * API used: https://jservice.io/api
     */

    /* https://jservice.io/api/random */
    @GET("random")
    Call<List<Trivium>> getRandomQuestions(@Query("count") int num);
}
