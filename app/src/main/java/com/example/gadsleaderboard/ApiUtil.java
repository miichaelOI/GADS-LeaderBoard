package com.example.gadsleaderboard;

import android.net.Uri;
import android.util.Log;

import com.example.gadsleaderboard.models.LearningLeader;
import com.example.gadsleaderboard.models.SkillLeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {

    private ApiUtil(){}

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";
    public static final String PATH = "/api/";

    public static URL buildUrl(String title){
        String fullUrl = BASE_API_URL + PATH + title ;

        URL url = null ;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(PATH, title)
            //    .appendQueryParameter(KEY, API_KEY)
                .build();
        try {
            url = new URL (fullUrl);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return url;

    }



    public static String getJson (URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        try{
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData){
                return scanner.next();
            }
            else{
                return null;
            }

        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }
    }



    public static ArrayList<LearningLeader> getLearnersFromJson(String json){

        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";





        ArrayList<LearningLeader> learningLeaders = new ArrayList<>();
        try{
            JSONArray jsonLearners = new JSONArray(json);
  //         JSONArray arrayLearners = jsonLearners.getJSONArray(ITEMS);
            int numberOfLearners = jsonLearners.length();

            for(int i =0; i < numberOfLearners; i++){
                JSONObject learnerJSON = jsonLearners.getJSONObject(i);


                LearningLeader learningLeader = new LearningLeader(
                        learnerJSON.getString(NAME),
                        learnerJSON.getInt(HOURS),
                        learnerJSON.getString(COUNTRY),
                        learnerJSON.getString(BADGEURL)
                );

                learningLeaders.add(learningLeader);

            }


        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return learningLeaders;
    }

    public static ArrayList<SkillLeader> getSkillFromJson(String json){

        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";


        ArrayList<SkillLeader> skillLeaders = new ArrayList<>();
        try{
            JSONArray jsonSkill = new JSONArray(json);

            int numberOfSkill = jsonSkill.length();

            for (int i = 0; i < numberOfSkill; i++){
                JSONObject skillJSON = jsonSkill.getJSONObject(i);

                SkillLeader skillLeader = new SkillLeader(
                        skillJSON.getString(NAME),
                        skillJSON.getInt(SCORE),
                        skillJSON.getString(COUNTRY),
                        skillJSON.getString(BADGEURL)
                );

                skillLeaders.add(skillLeader);



            }

        }
        catch (JSONException e){
            e.printStackTrace();


    }

        return skillLeaders;



}
}
