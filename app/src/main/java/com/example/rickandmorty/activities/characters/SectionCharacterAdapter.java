package com.example.rickandmorty.activities.characters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.model.CharacterResultModel;
import com.example.rickandmorty.model.SectionedModel;

import java.util.ArrayList;

public class SectionCharacterAdapter extends RecyclerView.Adapter<SectionCharacterAdapter.SectionViewHolder> {

    private ArrayList<SectionedModel> sectionModels; // TODO: Change the arraylist
    private Context context;
    private CharacterSelection characterSelection;

    SectionCharacterAdapter(ArrayList<SectionedModel> sectionModels, Context context, CharacterSelection characterSelection) {
        this.sectionModels = sectionModels;
        this.context = context;
        this.characterSelection = characterSelection;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_section_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {

        SectionedModel sectionedModel = sectionModels.get(position);

        holder.sectionName.setText(sectionedModel.getSectionName());

        holder.characterRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.characterRecyclerView.setAdapter(new CharacterAdapter(sectionedModel.getCharacterResultModelArrayList(), context, characterSelection));

    }

    @Override
    public int getItemCount() {
        if (sectionModels != null) {
            return sectionModels.size();
        }
        return 0;
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView sectionName;
        RecyclerView characterRecyclerView;

        SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            inflateViews(itemView);
        }

        private void inflateViews(View itemView) {

            sectionName = itemView.findViewById(R.id.sectionName);
            characterRecyclerView = itemView.findViewById(R.id.charactersRecyclerView);
        }
    }
}

class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<CharacterResultModel> characterResultModelArrayList;
    private Context context;
    private CharacterSelection characterSelection;

    CharacterAdapter(ArrayList<CharacterResultModel> characterResultModelArrayList, Context context, CharacterSelection characterSelection) {
        this.characterResultModelArrayList = characterResultModelArrayList;
        this.context = context;
        this.characterSelection = characterSelection;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_character_layout, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.characterName.setText(characterResultModelArrayList.get(position).getName());
        holder.status.setText(characterResultModelArrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        if (characterResultModelArrayList != null) {
            return characterResultModelArrayList.size();
        }
        return 0;
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        TextView characterName, status;
        CardView mainCardLayout;

        CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            inflateViews(itemView);
            setClickListeners();
        }

        private void inflateViews(View itemView) {

            mainCardLayout = itemView.findViewById(R.id.mainCardLayout);
            characterName = itemView.findViewById(R.id.characterName);
            status = itemView.findViewById(R.id.status);
        }

        private void setClickListeners() {
            mainCardLayout.setOnClickListener(view -> characterSelection.characterSelected(characterResultModelArrayList.get(getAdapterPosition())));
        }
    }
}