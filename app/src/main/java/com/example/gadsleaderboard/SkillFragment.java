package com.example.gadsleaderboard;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gadsleaderboard.adapters.MainRecyclerViewAdapter;
import com.example.gadsleaderboard.adapters.SkillRecyclerViewAdapter;
import com.example.gadsleaderboard.models.LearningLeader;
import com.example.gadsleaderboard.models.SkillLeader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class SkillFragment extends Fragment {

    private static final String TAG = "SkillFragment";

    private RecyclerView mRecyclerView;

    private ArrayList<SkillLeader> mSkillLeaders = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private SkillRecyclerViewAdapter mRecyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill, container, false);

        Log.d(TAG, "onCreateView: started");

        mRecyclerView = view.findViewById(R.id.recycler_view1);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        try{
            URL skillUrl = ApiUtil.buildUrl("skilliq");
            new SkillQueryTask().execute(skillUrl);
        }

        catch (Exception e){
            Log.d("error", e.getMessage());
        }


        return view;
    }


    public class SkillQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;

            try{
                result = ApiUtil.getJson(searchUrl);
            }

            catch(IOException e){
                Log.e("Error", e.getMessage());
            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            ArrayList<SkillLeader> skillLeaders = ApiUtil.getSkillFromJson(result);
            mRecyclerViewAdapter = new SkillRecyclerViewAdapter(getActivity(), skillLeaders);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);


        }
}}
