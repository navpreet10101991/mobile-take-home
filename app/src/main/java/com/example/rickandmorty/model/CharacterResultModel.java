package com.example.rickandmorty.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CharacterResultModel implements Parcelable {

    private long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private LocationData location;
    private String image;
    private ArrayList<String> episode = null;
    private String url;
    private String created;

    protected CharacterResultModel(Parcel in) {
        id = in.readLong();
        name = in.readString();
        status = in.readString();
        species = in.readString();
        type = in.readString();
        gender = in.readString();
        image = in.readString();
        episode = in.createStringArrayList();
        url = in.readString();
        created = in.readString();
    }

    public static final Creator<CharacterResultModel> CREATOR = new Creator<CharacterResultModel>() {
        @Override
        public CharacterResultModel createFromParcel(Parcel in) {
            return new CharacterResultModel(in);
        }

        @Override
        public CharacterResultModel[] newArray(int size) {
            return new CharacterResultModel[size];
        }
    };

    public CharacterResultModel() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public LocationData getLocation() {
        return location;
    }

    public void setLocation(LocationData location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList<String> episode) {
        this.episode = episode;
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
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(status);
        parcel.writeString(species);
        parcel.writeString(type);
        parcel.writeString(gender);
        parcel.writeString(image);
        parcel.writeStringList(episode);
        parcel.writeString(url);
        parcel.writeString(created);
    }
}

