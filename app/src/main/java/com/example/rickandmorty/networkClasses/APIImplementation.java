package com.example.rickandmorty.networkClasses;

import com.example.rickandmorty.model.CharacterResultModel;
import com.example.rickandmorty.networkClasses.responseInterface.CharacterResponseInterface;
import com.example.rickandmorty.networkClasses.responseInterface.EpisodeResponseInterface;

import java.util.ArrayList;

public class APIImplementation implements APIInterface {

    private EpisodeAPICall episodeAPICall;
    private EpisodeResponseInterface episodeResponseInterface;
    private CharacterResponseInterface characterResponseInterface;

    public APIImplementation(EpisodeResponseInterface episodeResponseInterface) {
        this.episodeResponseInterface = episodeResponseInterface;
    }

    public APIImplementation(CharacterResponseInterface characterResponseInterface) {
        this.characterResponseInterface = characterResponseInterface;
    }

    @Override
    public void fetchAllEpisodes() {

        episodeAPICall = new EpisodeAPICall(episodeResponseInterface);
        episodeAPICall.fetchAllEpisodes();
    }

    @Override
    public void fetchCharacters(ArrayList<String> characterList) {
        CharacterAPICall characterAPICall = new CharacterAPICall(characterResponseInterface);
        characterAPICall.fetchCharacterInEpisode(characterList);
    }
}
