package com.example.rickandmorty.activities.episodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.utilities.Keys;
import com.example.rickandmorty.utilities.Utilities;

import java.util.ArrayList;

class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private ArrayList<EpisodeResultModel> episodesArrayList;
    private Context context;
    private EpisodeSelection episodeSelection;

    EpisodeAdapter(ArrayList<EpisodeResultModel> episodesArrayList, Context context, EpisodeSelection episodeSelection) {
        this.episodesArrayList = episodesArrayList;
        this.context = context;
        this.episodeSelection = episodeSelection;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_episode_layout, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {

        holder.episodeName.setText(String.format(context.getString(R.string.stringName), episodesArrayList.get(position).getEpisode(), episodesArrayList.get(position).getName()));
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

        private CardView mainCardLayout;
        private TextView episodeName;
        private TextView createDate;

        EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);

            inflateViews(itemView);
            setClickListeners();
        }

        private void inflateViews(View itemView) {

            mainCardLayout = itemView.findViewById(R.id.mainCardLayout);
            episodeName = itemView.findViewById(R.id.episodeName);
            createDate = itemView.findViewById(R.id.createDate);
        }

        private void setClickListeners() {

            mainCardLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EpisodeResultModel episodeResultModel = episodesArrayList.get(getAdapterPosition());
                    episodeSelection.episodeSelected(episodeResultModel);
                }
            });
        }
    }
}