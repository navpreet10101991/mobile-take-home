package com.example.rickandmorty.networkClasses;

import android.os.AsyncTask;

import com.example.rickandmorty.model.EpisodeResultModel;
import com.example.rickandmorty.networkClasses.responseInterface.EpisodeResponseInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

class EpisodeAPICall {

    private EpisodeResponseInterface episodeResponseInterface;

    EpisodeAPICall(EpisodeResponseInterface episodeResponseInterface) {
        this.episodeResponseInterface = episodeResponseInterface;
    }

    void fetchAllEpisodes() {

        new FetchEpisodeClass().execute(ApiURL.EPISODE_URL);
    }

    private class FetchEpisodeClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder content = new StringBuilder();
            try {
                URL u = new URL(params[0]);
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
            return content.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            parseEpisodeJSON(result);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private void parseEpisodeJSON(String jsonString) {

        try {
            int length;
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("results")) {
                ArrayList<EpisodeResultModel> episodeResultModelArrayList = new ArrayList<>();
                EpisodeResultModel episodeResultModel;
                JSONArray jsonEpisodesArray = jsonObject.getJSONArray("results");
                length = jsonEpisodesArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonEpisodeObject = jsonEpisodesArray.getJSONObject(i);
                    episodeResultModel = new EpisodeResultModel();
                    // ID of the episode
                    if (jsonEpisodeObject.has("id") && !(jsonEpisodeObject.isNull("id"))) {
                        episodeResultModel.setId(jsonEpisodeObject.getInt("id"));
                    }

                    // Name
                    if (jsonEpisodeObject.has("name") && !(jsonEpisodeObject.isNull("name"))) {
                        episodeResultModel.setName(jsonEpisodeObject.getString("name"));
                    }

                    // Episode
                    if (jsonEpisodeObject.has("episode") && !(jsonEpisodeObject.isNull("episode"))) {
                        episodeResultModel.setEpisode(jsonEpisodeObject.getString("episode"));
                    }

                    // Created
                    if (jsonEpisodeObject.has("created") && !(jsonEpisodeObject.isNull("created"))) {
                        episodeResultModel.setCreated(jsonEpisodeObject.getString("created"));
                    }

                    // List of characters
                    ArrayList<String> characterArrayList = new ArrayList<>();
                    if (jsonEpisodeObject.has("characters") && !(jsonEpisodeObject.isNull("characters"))) {
                        JSONArray jsonCharacterArray = jsonEpisodeObject.getJSONArray("characters");
                        for (int j = 0; j < jsonCharacterArray.length(); j++) {
                            characterArrayList.add(jsonCharacterArray.getString(j));
                        }
                    }
                    episodeResultModel.setCharacters(characterArrayList);

                    episodeResultModelArrayList.add(episodeResultModel);
                }
                episodeResponseInterface.episodeList(episodeResultModelArrayList);
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
}