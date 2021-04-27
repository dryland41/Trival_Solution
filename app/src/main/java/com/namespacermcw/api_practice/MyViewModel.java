package com.namespacermcw.api_practice;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {
    private final String TAG = "_WORK";

    //STEP 1: Created Repositories that will fetch our data
    private final JokeRepository jokeRepository = new JokeRepository();
    private final TriviaRepository triviaRepository = new TriviaRepository();

    //STEP 2: Created MutableLiveData objects to carry and emit our data
    private MutableLiveData<Joke> headlineJoke;
    private MutableLiveData<List<Joke>> jokes;
    private MutableLiveData<List<Trivium>> trivia;

    //STEP 3: Created methods that access our MutableLiveData objects read-only

    /**
     * Accessor method for jokes MutableLiveData
     * emits to observers whenever jokes is updated
     */
    public LiveData<List<Joke>> getJokes() {
        if (jokes == null) {
            jokes = new MutableLiveData<>();
            loadJokes();
        }

        return jokes;
    }

    /**
     * Accessor method for trivia MutableLiveData
     * emits to observers whenever jokes is updated
     */
    public LiveData<List<Trivium>> getTrivia() {
        if (trivia == null) {
            trivia = new MutableLiveData<>();
            loadTrivia();
        }

        return trivia;
    }

    /**
     * Updates jokes MutableLiveData with a list of 10 random Jokes returned from JokeRepository's ApiService
     */
    private void loadJokes() {
        jokeRepository.getTenRandomJokesCall().enqueue(new Callback<List<Joke>>() {
            @Override
            public void onResponse(@NotNull Call<List<Joke>> call, @NotNull Response<List<Joke>> response) {
                if (response.body() != null) {
                    jokes.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Joke>> call, @NotNull Throwable t) {
                Log.e(TAG, "Random jokes call failed");
            }
        });
    }

    /**
     * Updates trivia MutableLiveData with a list of 10 random questions returned from TriviaRepository's ApiService
     */
    private void loadTrivia() {
        triviaRepository.getTenRandomQuestionsCall().enqueue(new Callback<List<Trivium>>() {
            @Override
            public void onResponse(@NotNull Call<List<Trivium>> call, @NotNull Response<List<Trivium>> response) {
                if (response.body() != null) {
                    trivia.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Trivium>> call, @NotNull Throwable t) {
                Log.e(TAG, "Random questions call failed");
            }
        });
    }
}
