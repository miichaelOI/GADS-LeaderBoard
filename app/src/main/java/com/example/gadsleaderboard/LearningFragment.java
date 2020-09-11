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
import com.example.gadsleaderboard.models.LearningLeader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class LearningFragment extends Fragment {

    private static final String TAG = "LearningFragment";

    private RecyclerView mRecyclerView;

   private ArrayList<LearningLeader> mLearningLeaders = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private MainRecyclerViewAdapter mRecyclerViewAdapter;
   // ArrayList<Book> books = ApiUtil.getBooksFromJson(result);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);

        Log.d(TAG, "onCreateView: started");

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        try{
            URL learningUrl = ApiUtil.buildUrl("hours");
            new LearningQueryTask().execute(learningUrl);
        }

        catch (Exception e){
            Log.d("error", e.getMessage());
        }


        return view;

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
       // mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), mLearningLeaders);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);



    }

    public class LearningQueryTask extends AsyncTask<URL, Void, String>{
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
            ArrayList<LearningLeader> learningLeaders = ApiUtil.getLearnersFromJson(result);
            mRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), learningLeaders);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);


        }
    }
}
