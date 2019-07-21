package com.example.rickandmorty.activities.characters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rickandmorty.activities.characterDetail.CharacterDetailActivity;
import com.example.rickandmorty.model.CharacterResultModel;
import com.example.rickandmorty.model.EpisodeResultModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.model.SectionedModel;

import java.util.ArrayList;

public class CharactersActivity extends AppCompatActivity {

    private CharactersPresenter charactersPresenter;
    private RecyclerView charactersRecyclerView;
    private ProgressBar progressBar;

    public static Intent createIntent(Context context, EpisodeResultModel episodeResultModel) {

        Intent intent = new Intent(context, CharactersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("episode", episodeResultModel);
        intent.putExtra("data", bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        charactersPresenter = new CharactersPresenter(this);
        inflateViews();

        getData();
    }

    private void inflateViews() {
        charactersRecyclerView = findViewById(R.id.charactersRecyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                EpisodeResultModel episodeResultModel = bundle.getParcelable("episode");
                if (episodeResultModel != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    charactersPresenter.fetchCharacters(episodeResultModel);
                }
            }
        }
    }

    void setRecyclerViewAdapter(ArrayList<SectionedModel> characterResultModelArrayList) {
        charactersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SectionCharacterAdapter sectionCharacterAdapter = new SectionCharacterAdapter(characterResultModelArrayList, this, characterResultModel -> startActivity(CharacterDetailActivity.newIntent(CharactersActivity.this, characterResultModel)));
        charactersRecyclerView.setAdapter(sectionCharacterAdapter);
    }

    void displayProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

interface CharacterSelection {
    void characterSelected(CharacterResultModel characterResultModel);
}
