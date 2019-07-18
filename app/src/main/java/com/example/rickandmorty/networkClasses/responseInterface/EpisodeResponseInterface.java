package com.example.rickandmorty.networkClasses.responseInterface;

import com.example.rickandmorty.model.EpisodeResultModel;

import java.util.ArrayList;

public interface EpisodeResponseInterface {
    void episodeList(ArrayList<EpisodeResultModel> episodeList);
}
