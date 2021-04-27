package com.namespacermcw.api_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TriviaRecyclerViewAdapter extends RecyclerView.Adapter<TriviaRecyclerViewAdapter.ViewHolder> {
    private AdapterListener listener;
    List<Trivium> triviaList;

    /**
     * Inflating the layout the ViewHolder will be using for each list item
     * and creating the ViewHolder with it
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trivia_list_item, parent, false);

        return new ViewHolder(view);
    }

    /**
     * Uses the viewHolder to bind data to each list item
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTriviaListItem(triviaList.get(position));
    }

    /**
     * Provides a count of the list used by the adapter to assign data to each list item view
     */
    @Override
    public int getItemCount() {
        if (triviaList == null)
            return 0;

        return triviaList.size();
    }

    /**
     * Updates the list used by the adapter to assign data to each list item view
     */
    public void updateTrivia(List<Trivium> newTrivia) {
        triviaList = newTrivia;
        notifyDataSetChanged();
    }

    /**
     * Sets a listener to be notified when a list item is clicked
     */
    public void setAdapterListener(AdapterListener adapterListener) {
        listener = adapterListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView triviaItemQuestion;
        private TextView triviaItemAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            triviaItemQuestion = itemView.findViewById(R.id.trivia_item_question);
            triviaItemAnswer = itemView.findViewById(R.id.trivia_item_answer);
        }

        /**
         * Binds data from the Trivium object passed to widgets in the list item layout
         * and sets the ViewHolder's onClickListener to the jokeItemPunchline widget
         */
        public void bindTriviaListItem(Trivium trivia) {
            triviaItemQuestion.setText(trivia.getQuestion());
            triviaItemQuestion.setOnClickListener(this);
            triviaItemAnswer.setText(trivia.getAnswer());
        }

        /**
         * Notifies the AdapterListener that an item has been clicked, providing the item position
         * and reveals the jokeItemPunchline widget
         */
        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onItemClicked(getAdapterPosition());

            triviaItemAnswer.setVisibility(View.VISIBLE);
        }
    }
}
