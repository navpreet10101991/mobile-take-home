package com.example.rickandmorty.activities.episodes;

import android.os.Bundle;

import com.example.rickandmorty.R;
import com.example.rickandmorty.activities.characters.CharactersActivity;
import com.example.rickandmorty.model.EpisodeResultModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity {

    private EpisodePresenter episodePresenter;
    private RecyclerView episodeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        episodePresenter = new EpisodePresenter(this);
        inflateViews();
        fetchEpisodes();
    }

    private void inflateViews() {
        episodeRecyclerView = findViewById(R.id.episodeRecyclerView);
    }

    private void fetchEpisodes() {
        episodePresenter.fetchEpisodes();
    }

    void setRecyclerViewAdapter(ArrayList<EpisodeResultModel> episodeResultModelArrayList) {
        episodeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EpisodeAdapter episodeAdapter = new EpisodeAdapter(episodeResultModelArrayList, this, new EpisodeSelection() {

            @Override
            public void episodeSelected(EpisodeResultModel episodeResultModel) {
                startActivity(CharactersActivity.createIntent(EpisodesActivity.this, episodeResultModel));
            }
        });
        episodeRecyclerView.setAdapter(episodeAdapter);
    }
}

interface EpisodeSelection {
    void episodeSelected(EpisodeResultModel episodeResultModel);
}