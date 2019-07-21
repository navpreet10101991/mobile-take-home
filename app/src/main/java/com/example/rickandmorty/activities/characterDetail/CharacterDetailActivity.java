package com.example.rickandmorty.activities.characterDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.rickandmorty.model.CharacterResultModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.rickandmorty.R;

public class CharacterDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView name, status;

    public static Intent newIntent(Context context, CharacterResultModel characterResultModel) {

        Intent intent = new Intent(context, CharacterDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("character", characterResultModel);
        intent.putExtra("data", bundle);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inflateViews();
        showDetails();
    }

    private void inflateViews() {

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
    }

    private void showDetails() {

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                CharacterResultModel characterResultModel = bundle.getParcelable("character");
                if (characterResultModel != null) {
                    setFields(characterResultModel);
                }
            }
        }

    }

    private void setFields(CharacterResultModel characterResultModel) {

        imageView.setImageResource(R.drawable.ic_launcher_background);
        name.setText(characterResultModel.getName());
        status.setText(characterResultModel.getStatus());
    }
}