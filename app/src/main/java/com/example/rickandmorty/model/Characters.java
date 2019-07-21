package com.example.rickandmorty.model;

import java.util.ArrayList;

public class Characters {

    private InfoModel info;
    private ArrayList<CharacterResultModel> results = null;

    public InfoModel getInfo() {
        return info;
    }

    public void setInfo(InfoModel info) {
        this.info = info;
    }

    public ArrayList<CharacterResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<CharacterResultModel> results) {
        this.results = results;
    }
}
