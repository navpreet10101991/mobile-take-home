package com.example.rickandmorty.activities.characters;

import com.example.rickandmorty.model.CharacterResultModel;
import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.model.SectionedModel;
import com.example.rickandmorty.networkClasses.APIImplementation;
import com.example.rickandmorty.networkClasses.responseInterface.CharacterResponseInterface;

import java.util.ArrayList;
import java.util.Collections;

class CharactersPresenter {

    private CharactersActivity charactersActivity;

    CharactersPresenter(CharactersActivity charactersActivity) {
        this.charactersActivity = charactersActivity;
    }

    /**
     * Fetch Characters
     * @param episodeResultModel episode Result model
     */
    void fetchCharacters(EpisodeResultModel episodeResultModel) {

        charactersActivity.displayProgressBar(true);
        new APIImplementation((CharacterResponseInterface) characterList -> {
            charactersActivity.setRecyclerViewAdapter(splitCharacters(characterList));
            charactersActivity.displayProgressBar(false);
        }).fetchCharacters(episodeResultModel.getCharacters());
    }

    /**
     * Separate the Array list into 2 sections with status 'Alive' or 'Dead'
     * @param characterResultModelArrayList full arraylist of characters
     * @return ArrayList SectionedModel
     */
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

        Collections.sort(aliveModelArrayList, (o1, o2) -> o1.getCreatedDate().compareTo(o2.getCreatedDate()));
        Collections.sort(deadModelArrayList, (o1, o2) -> o1.getCreatedDate().compareTo(o2.getCreatedDate()));

        sectionedModelArrayList.add(new SectionedModel("Alive", aliveModelArrayList));
        sectionedModelArrayList.add(new SectionedModel("Dead", deadModelArrayList));

        return sectionedModelArrayList;
    }
}