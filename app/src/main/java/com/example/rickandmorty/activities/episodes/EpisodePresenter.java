package com.example.rickandmorty.activities.episodes;

import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.networkClasses.APIImplementation;
import com.example.rickandmorty.networkClasses.responseInterface.EpisodeResponseInterface;

import java.util.ArrayList;

class EpisodePresenter {

    private EpisodesActivity episodesActivity;

    EpisodePresenter(EpisodesActivity episodesActivity) {
        this.episodesActivity = episodesActivity;
    }

    void fetchEpisodes() {

        APIImplementation apiImplementation = new APIImplementation(new EpisodeResponseInterface() {
            @Override
            public void episodeList(ArrayList<EpisodeResultModel> episodeList) {
                episodesActivity.setRecyclerViewAdapter(episodeList);
            }
        });
        apiImplementation.fetchAllEpisodes();
    }
}
