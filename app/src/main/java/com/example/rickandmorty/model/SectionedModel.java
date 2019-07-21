package com.example.rickandmorty.model;

import java.util.ArrayList;

public class SectionedModel {

    private String sectionName;
    private ArrayList<CharacterResultModel> characterResultModelArrayList;

    public SectionedModel(String sectionName, ArrayList<CharacterResultModel> characterResultModelArrayList) {
        this.sectionName = sectionName;
        this.characterResultModelArrayList = characterResultModelArrayList;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public ArrayList<CharacterResultModel> getCharacterResultModelArrayList() {
        return characterResultModelArrayList;
    }

    public void setCharacterResultModelArrayList(ArrayList<CharacterResultModel> characterResultModelArrayList) {
        this.characterResultModelArrayList = characterResultModelArrayList;
    }
}
