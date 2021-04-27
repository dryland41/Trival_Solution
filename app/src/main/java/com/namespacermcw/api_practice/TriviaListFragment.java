package com.namespacermcw.api_practice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TriviaListFragment extends Fragment implements AdapterListener {

    private MyViewModel viewModel;
    private RecyclerView triviaRecyclerView;
    private TriviaRecyclerViewAdapter adapter;
    private List<Trivium> triviaList;

    public static TriviaListFragment newInstance() {
        return new TriviaListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trivia_list, container, false);

        //STEP 1: Assigned RecyclerView
        triviaRecyclerView = view.findViewById(R.id.trivia_recycler_view);

        //STEP 2: Created Adapter and set this Fragment as the onAdapterListener
        adapter = new TriviaRecyclerViewAdapter();
        adapter.setAdapterListener(this);

        //STEP 3: Set Adapter for the RecyclerView
        triviaRecyclerView.setAdapter(adapter);

        //STEP 4: Added ItemDecoration to RecyclerView so each list item would be separated
        // by line
        triviaRecyclerView.addItemDecoration(new DividerItemDecoration(
                view.getContext(),
                DividerItemDecoration.VERTICAL
        ));

        //STEP 5: Set LinearLayoutManager for the RecyclerView
        triviaRecyclerView.setLayoutManager(
                new LinearLayoutManager(
                        view.getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                ));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //STEP 6: Created the ViewModel and set  the host Activity of this Fragment
        // as the LifeCycleOwner to observe
        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        //STEP 7: Created observers that trigger methods to present a question
        viewModel.getTrivia().observe(requireActivity(), this::newSetOfTrivia);
    }

    /**
     * Updating the list of questions with the newly arrived list
     */
    private void newSetOfTrivia(List<Trivium> trivia) {
        triviaList = trivia;
        adapter.updateTrivia(triviaList);
    }

    /**
     * CHECK LOGS: Proof that this fragment knows which question has been clicked
     */
    @Override
    public void onItemClicked(int position) {
        Log.d("_WORK", "The question in the " + position + " position has been clicked!");
    }
}