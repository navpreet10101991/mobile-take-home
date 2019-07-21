package com.example.rickandmorty.activities.characterDetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.rickandmorty.model.CharacterResultModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.rickandmorty.R;

public class CharacterDetailActivity extends AppCompatActivity {

    private CharacterDetailPresenter characterDetailPresenter;
    private ImageView imageView;
    private TextView name, status, gender, species, type, createdOn;

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

        characterDetailPresenter = new CharacterDetailPresenter(this);

        inflateViews();
        showDetails();
    }

    private void inflateViews() {

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        gender = findViewById(R.id.gender);
        species = findViewById(R.id.species);
        type = findViewById(R.id.type);
        createdOn = findViewById(R.id.createdOn);
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

        name.setText(String.format(getString(R.string.stringName), "Name", characterResultModel.getName()));
        status.setText(String.format(getString(R.string.stringName), "Status", characterResultModel.getStatus()));
        gender.setText(String.format(getString(R.string.stringName), "Gender", characterResultModel.getGender()));
        species.setText(String.format(getString(R.string.stringName), "Species", characterResultModel.getSpecies()));
        type.setText(String.format(getString(R.string.stringName), "Type", characterResultModel.getType()));
        createdOn.setText(String.format(getString(R.string.stringName), "Created On", characterResultModel.getCreated()));

        characterDetailPresenter.getImage(characterResultModel.getImage());
    }

    void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}