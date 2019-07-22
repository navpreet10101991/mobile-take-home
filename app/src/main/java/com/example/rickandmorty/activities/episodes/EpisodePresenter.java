package com.example.rickandmorty.activities.episodes;

import com.example.rickandmorty.networkClasses.APIImplementation;
import com.example.rickandmorty.networkClasses.responseInterface.EpisodeResponseInterface;

class EpisodePresenter {

    private EpisodesActivity episodesActivity;

    EpisodePresenter(EpisodesActivity episodesActivity) {
        this.episodesActivity = episodesActivity;
    }

    /**
     * Episode from API Call
     */
    void fetchEpisodes() {

        APIImplementation apiImplementation = new APIImplementation((EpisodeResponseInterface) episodeList -> episodesActivity.setRecyclerViewAdapter(episodeList));
        apiImplementation.fetchAllEpisodes();
    }
}