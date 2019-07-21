package com.example.rickandmorty.networkClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rickandmorty.model.CharacterResultModel;
import com.example.rickandmorty.model.LocationData;
import com.example.rickandmorty.model.Origin;
import com.example.rickandmorty.networkClasses.responseInterface.CharacterDetailResponseInterface;
import com.example.rickandmorty.networkClasses.responseInterface.CharacterResponseInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class CharacterAPICall {

    private CharacterResponseInterface characterResponseInterface;
    private CharacterDetailResponseInterface characterDetailResponseInterface;

    CharacterAPICall(CharacterResponseInterface characterResponseInterface) {
        this.characterResponseInterface = characterResponseInterface;
    }

    CharacterAPICall(CharacterDetailResponseInterface characterDetailResponseInterface) {
        this.characterDetailResponseInterface = characterDetailResponseInterface;
    }

    void fetchCharacterInEpisode(ArrayList<String> url) {

        new CharacterAPICall.FetchCharacterClass().execute(url);
    }

    private class FetchCharacterClass extends AsyncTask<ArrayList<String>, Void, ArrayList<CharacterResultModel>> {

        @Override
        protected ArrayList<CharacterResultModel> doInBackground(ArrayList<String>... params) {

            ArrayList<String> urlList = params[0];
            ArrayList<CharacterResultModel> characterResultModels = new ArrayList<>();
            for (String urlString : urlList) {
                StringBuilder content = new StringBuilder();
                try {

                    URL u = new URL(urlString);
                    HttpURLConnection uc = (HttpURLConnection) u.openConnection();
                    if (uc.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = uc.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                        String line;
                        while ((line = br.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                    } else {
                        throw new IOException(uc.getResponseMessage());
                    }
                } catch (StackOverflowError | Exception s) {
                    s.printStackTrace();
                } catch (Error e) {
                    e.printStackTrace();
                }
                characterResultModels.add(parseCharacterJSON(content.toString()));
            }
            return characterResultModels;
        }

        @Override
        protected void onPostExecute(ArrayList<CharacterResultModel> characterResultModels) {
            characterResponseInterface.characterList(characterResultModels);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private CharacterResultModel parseCharacterJSON(String jsonString) {

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            CharacterResultModel characterResultModel = new CharacterResultModel();
            // ID of the episode
            if (jsonObject.has("id") && !jsonObject.isNull("id")) {
                characterResultModel.setId(jsonObject.getInt("id"));
            }

            // Name
            if (jsonObject.has("name") && !(jsonObject.isNull("name"))) {
                characterResultModel.setName(jsonObject.getString("name"));
            }

            // Status
            if (jsonObject.has("status") && !(jsonObject.isNull("status"))) {
                characterResultModel.setStatus(jsonObject.getString("status"));
            }

            // Species
            if (jsonObject.has("species") && !(jsonObject.isNull("species"))) {
                characterResultModel.setSpecies(jsonObject.getString("species"));
            }

            // Type
            if (jsonObject.has("type") && !(jsonObject.isNull("type"))) {
                characterResultModel.setType(jsonObject.getString("type"));
            }

            // Gender
            if (jsonObject.has("gender") && !(jsonObject.isNull("gender"))) {
                characterResultModel.setGender(jsonObject.getString("gender"));
            }

            // Origin
            if (jsonObject.has("origin") && !(jsonObject.isNull("origin"))) {
                Origin origin = new Origin();
                JSONObject originJSONObject = jsonObject.getJSONObject("origin");
                if (originJSONObject.has("name") && !(jsonObject.isNull("name"))) {
                    origin.setName(originJSONObject.getString("name"));
                }
                if (originJSONObject.has("url") && !(jsonObject.isNull("url"))) {
                    origin.setUrl(originJSONObject.getString("url"));
                }
                characterResultModel.setOrigin(origin);
            }

            // Location
            if (jsonObject.has("location") && !(jsonObject.isNull("location"))) {
                LocationData locationData = new LocationData();
                JSONObject originJSONObject = jsonObject.getJSONObject("location");
                if (originJSONObject.has("name") && !(jsonObject.isNull("name"))) {
                    locationData.setName(originJSONObject.getString("name"));
                }
                if (originJSONObject.has("url") && !(jsonObject.isNull("url"))) {
                    locationData.setUrl(originJSONObject.getString("url"));
                }
                characterResultModel.setLocation(locationData);
            }

            // Image
            if (jsonObject.has("image") && !(jsonObject.isNull("image"))) {
                characterResultModel.setImage(jsonObject.getString("image"));
            }

            // Create Date
            if (jsonObject.has("created") && !(jsonObject.isNull("created"))) {
                characterResultModel.setCreated(jsonObject.getString("created"));
            }

            // List of Episodes
            ArrayList<String> episodeArrayList = new ArrayList<>();
            if (jsonObject.has("episode") && !(jsonObject.isNull("episode"))) {
                JSONArray jsonEpisodeArray = jsonObject.getJSONArray("episode");
                for (int j = 0; j < jsonEpisodeArray.length(); j++) {
                    episodeArrayList.add(jsonEpisodeArray.getString(j));
                }
            }
            characterResultModel.setEpisode(episodeArrayList);
            return characterResultModel;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return null;
    }

    /*
     * Character Details
     * */
    // Image
    void getImage(String url) {

        new DownloadImageTask().execute(url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap image = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return image;
        }

        protected void onPostExecute(Bitmap result) {
            characterDetailResponseInterface.characterImage(result);
        }
    }
}