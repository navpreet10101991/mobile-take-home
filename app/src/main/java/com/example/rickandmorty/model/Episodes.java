package com.example.rickandmorty.model;

import java.util.ArrayList;

public class Episodes {

    private InfoModel info;
    private ArrayList<EpisodeResultModel> results = null;

    public InfoModel getInfo() {
        return info;
    }

    public void setInfo(InfoModel info) {
        this.info = info;
    }

    public ArrayList<EpisodeResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<EpisodeResultModel> results) {
        this.results = results;
    }
}