package com.namespacermcw.api_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        //STEP 1: Placed fragments into the Activity's fragment containers
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.trivia_list_frag_container, TriviaListFragment.newInstance())
                    .commit();
        }

        //STEP 2: Created the ViewModel and set this Activity
        // as the lifecycle owner to observe
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //STEP 3: Created observers that trigger relevant methods
        viewModel.getTrivia().observe(this, this::newSetOfTrivia);

    }

    private void newSetOfTrivia(List<Trivium> trivia) {
        Log.d("_WORK", "A new list of questions have reached the host activity with a size of: " + trivia.size());
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}