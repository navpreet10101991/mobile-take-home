package com.example.rickandmorty.activities.characterDetail;

import android.graphics.Bitmap;

import com.example.rickandmorty.networkClasses.APIImplementation;
import com.example.rickandmorty.networkClasses.responseInterface.CharacterDetailResponseInterface;

class CharacterDetailPresenter implements CharacterDetailResponseInterface {

    private CharacterDetailActivity characterDetailActivity;
    private APIImplementation apiImplementation = new APIImplementation(this);

    CharacterDetailPresenter(CharacterDetailActivity characterDetailActivity) {
        this.characterDetailActivity = characterDetailActivity;
    }

    void getImage(String url) {
        apiImplementation.fetchCharacterImage(url);
    }

    @Override
    public void characterImage(Bitmap bitmap) {
        characterDetailActivity.setImage(bitmap);
    }
}
