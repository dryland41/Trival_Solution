package com.namespacermcw.api_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JokeRecyclerViewAdapter extends RecyclerView.Adapter<JokeRecyclerViewAdapter.ViewHolder> {
    private AdapterListener listener;
    List<Joke> jokeList;

    /**
     * Inflating the layout the ViewHolder will be using for each list item
     * and creating the ViewHolder with it
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.joke_list_item, parent, false);

        return new ViewHolder(view);
    }

    /**
     * Uses the viewHolder to bind data to each list item
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindJokeListItem(jokeList.get(position));
    }

    /**
     * Provides a count of the list used by the adapter to assign data to each list item view
     */
    @Override
    public int getItemCount() {
        if (jokeList == null)
            return 0;

        return jokeList.size();
    }

    /**
     * Updates the list used by the adapter to assign daya to each list item view
     */
    public void updateJokes(List<Joke> newJokes) {
        jokeList = newJokes;
        notifyDataSetChanged();
    }

    /**
     * Sets a listener to be notified when a list item is clicked
     */
    public void setAdapterListener(AdapterListener adapterListener) {
        listener = adapterListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView jokeItemSetup;
        private TextView jokeItemPunchline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            jokeItemSetup = itemView.findViewById(R.id.joke_item_set_up);
            jokeItemPunchline = itemView.findViewById(R.id.joke_item_punchline);
        }

        /**
         * Binds data from the Joke object passed to widgets in the list item layout
         * and sets the ViewHolder's onClickListener to the jokeItemPunchline widget
         */
        public void bindJokeListItem(Joke joke) {
            jokeItemSetup.setText(joke.getSetup());
            jokeItemSetup.setOnClickListener(this);
            jokeItemPunchline.setText(joke.getPunchline());
        }

        /**
         * Notifies the AdapterListener that an item has been clicked, providing the item position
         * and reveals the jokeItemPunchline widget
         */
        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onItemClicked(getAdapterPosition());

            jokeItemPunchline.setVisibility(View.VISIBLE);
        }
    }
}
