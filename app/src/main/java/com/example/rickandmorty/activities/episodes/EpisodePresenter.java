package com.example.rickandmorty.activities.episodes;

import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.networkClasses.APIImplementation;
import com.example.rickandmorty.networkClasses.responseInterface.EpisodeResponseInterface;

import java.util.ArrayList;

class EpisodePresenter {

    private APIImplementation apiImplementation;
    private EpisodesActivity episodesActivity;

    EpisodePresenter(EpisodesActivity episodesActivity) {
        this.episodesActivity = episodesActivity;
    }

    void fetchEpisodes() {

        apiImplementation = new APIImplementation(episodesActivity, new EpisodeResponseInterface() {
            @Override
            public void episodeList(ArrayList<EpisodeResultModel> episodeList) {
                episodesActivity.setRecyclerViewAdapter(episodeList);
            }
        });
        apiImplementation.fetchAllEpisodes();
    }
}
