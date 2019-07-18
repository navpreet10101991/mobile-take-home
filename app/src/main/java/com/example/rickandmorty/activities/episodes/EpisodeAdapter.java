package com.example.rickandmorty.activities.episodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.model.Episodes;
import com.example.rickandmorty.utilities.Keys;
import com.example.rickandmorty.utilities.Utilities;

import java.security.Key;
import java.util.ArrayList;

class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private ArrayList<EpisodeResultModel> episodesArrayList;
    private Context context;

    EpisodeAdapter(ArrayList<EpisodeResultModel> episodesArrayList, Context context) {
        this.episodesArrayList = episodesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_episode_layout, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {

        holder.episodeName.setText(String.format(context.getString(R.string.episodeName), episodesArrayList.get(position).getEpisode(), episodesArrayList.get(position).getName()));
        holder.createDate.setText(Utilities.changeDateStyle(episodesArrayList.get(position).getCreated(), Keys.NETWORKING_INCOMING_FORMAT, Keys.DISPLAYING_FORMAT));
    }

    @Override
    public int getItemCount() {
        if (episodesArrayList != null) {
            return episodesArrayList.size();
        }
        return 0;
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder {

        private TextView episodeName;
        private TextView createDate;

        EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);

            inflateViews(itemView);
        }

        private void inflateViews(View itemView) {

            episodeName = itemView.findViewById(R.id.episodeName);
            createDate = itemView.findViewById(R.id.createDate);
        }
    }
}
