package com.example.rickandmorty.networkClasses;

import java.util.ArrayList;

public interface APIInterface {

    void fetchAllEpisodes();
    void fetchCharacters(ArrayList<String> characterList);
    void fetchCharacterImage(String url);
}