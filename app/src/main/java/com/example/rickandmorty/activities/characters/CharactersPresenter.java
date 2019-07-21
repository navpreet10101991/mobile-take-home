package com.example.rickandmorty.activities.characters;

import com.example.rickandmorty.model.CharacterResultModel;
import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.model.SectionedModel;
import com.example.rickandmorty.networkClasses.APIImplementation;
import com.example.rickandmorty.networkClasses.responseInterface.CharacterResponseInterface;

import java.util.ArrayList;

class CharactersPresenter {

    private CharactersActivity charactersActivity;

    CharactersPresenter(CharactersActivity charactersActivity) {
        this.charactersActivity = charactersActivity;
    }

    void fetchCharacters(EpisodeResultModel episodeResultModel) {

        charactersActivity.displayProgressBar(true);
        new APIImplementation((CharacterResponseInterface) characterList -> {
            charactersActivity.setRecyclerViewAdapter(splitCharacters(characterList));
            charactersActivity.displayProgressBar(false);
        }).fetchCharacters(episodeResultModel.getCharacters());
    }

    private ArrayList<SectionedModel> splitCharacters(ArrayList<CharacterResultModel> characterResultModelArrayList) {

        ArrayList<SectionedModel> sectionedModelArrayList = new ArrayList<>();
        ArrayList<CharacterResultModel> deadModelArrayList = new ArrayList<>();
        ArrayList<CharacterResultModel> aliveModelArrayList = new ArrayList<>();

        CharacterResultModel characterResultModel;
        for (int i = 0; i < characterResultModelArrayList.size(); i++) {
            characterResultModel = characterResultModelArrayList.get(i);
            if (characterResultModel.getStatus().equalsIgnoreCase("Alive")) {
                aliveModelArrayList.add(characterResultModel);
            } else {
                deadModelArrayList.add(characterResultModel);
            }
        }

        sectionedModelArrayList.add(new SectionedModel("Alive", aliveModelArrayList));
        sectionedModelArrayList.add(new SectionedModel("Dead", deadModelArrayList));

        return sectionedModelArrayList;
    }
}