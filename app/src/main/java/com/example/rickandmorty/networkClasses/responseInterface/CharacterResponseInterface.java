package com.example.rickandmorty.networkClasses.responseInterface;

import com.example.rickandmorty.model.CharacterResultModel;

import java.util.ArrayList;

public interface CharacterResponseInterface {
    void characterList(ArrayList<CharacterResultModel> characterList);
}
