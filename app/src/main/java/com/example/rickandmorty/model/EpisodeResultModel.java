package com.example.rickandmorty.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class EpisodeResultModel implements Parcelable {
    
    private Integer id;
    private String name;
    private String air_date;
    private String episode;
    private ArrayList<String> characters = null;
    private String url;
    private String created;

    public EpisodeResultModel() {

    }

    protected EpisodeResultModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        air_date = in.readString();
        episode = in.readString();
        characters = in.createStringArrayList();
        url = in.readString();
        created = in.readString();
    }

    public static final Creator<EpisodeResultModel> CREATOR = new Creator<EpisodeResultModel>() {
        @Override
        public EpisodeResultModel createFromParcel(Parcel in) {
            return new EpisodeResultModel(in);
        }

        @Override
        public EpisodeResultModel[] newArray(int size) {
            return new EpisodeResultModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<String> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        parcel.writeString(air_date);
        parcel.writeString(episode);
        parcel.writeStringList(characters);
        parcel.writeString(url);
        parcel.writeString(created);
    }
}
