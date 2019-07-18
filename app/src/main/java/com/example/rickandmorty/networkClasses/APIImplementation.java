package com.example.rickandmorty.networkClasses;

import android.content.Context;

import com.example.rickandmorty.networkClasses.responseInterface.EpisodeResponseInterface;

public class APIImplementation implements APIInterface {

    private Context context;
    private EpisodeAPICall episodeAPICall;
    private EpisodeResponseInterface episodeResponseInterface;

    public APIImplementation(Context context, EpisodeResponseInterface episodeResponseInterface) {
        this.context = context;
        this.episodeResponseInterface = episodeResponseInterface;
    }
    @Override
    public void fetchAllEpisodes() {

        episodeAPICall = new EpisodeAPICall(context, episodeResponseInterface);
        episodeAPICall.fetchAllEpisodes();
    }
}
