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

    /**
     * This method creates the intent to this activity
     * @param context Context of the activity from which to start this activity
     * @param episodeResultModel Bundle Data
     * @return Intent representing this activity
     */
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

    /**
     * Inflate Views of the activity
     */
    private void inflateViews() {
        charactersRecyclerView = findViewById(R.id.charactersRecyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    /**
     * Fetch the data from the Intent received
     * Call Presenter's method to fetch characters of the selected character.
     */
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

    /**
     * Show the data in the Recycler View
     * @param characterResultModelArrayList list of characters
     */
    void setRecyclerViewAdapter(ArrayList<SectionedModel> characterResultModelArrayList) {
        charactersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SectionCharacterAdapter sectionCharacterAdapter = new SectionCharacterAdapter(characterResultModelArrayList, this, characterResultModel -> startActivity(CharacterDetailActivity.newIntent(CharactersActivity.this, characterResultModel)));
        charactersRecyclerView.setAdapter(sectionCharacterAdapter);
    }

    /**
     *  Show/Hide Progress Bar to indicate loading of characters
     * @param isVisible if true then show the progress bar and false then hide the progress bar
     */
    void displayProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

/**
 *  Callback for character tap
 */
interface CharacterSelection {
    void characterSelected(CharacterResultModel characterResultModel);
}