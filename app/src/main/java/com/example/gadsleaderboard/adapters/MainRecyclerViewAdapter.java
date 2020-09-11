package com.example.gadsleaderboard.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.models.LearningLeader;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MainRecyclerViewAdapter";

    private Context mContext;
    private ArrayList<LearningLeader> mLearningLeaders;

    public MainRecyclerViewAdapter(Context context,ArrayList<LearningLeader> mLearningLeaders){
        this.mContext = context;
         this.mLearningLeaders = mLearningLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_feed,parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        LearningLeader leader = mLearningLeaders.get(position);
        holder.bind(leader);

    }

    @Override
    public int getItemCount() {
        return mLearningLeaders != null? mLearningLeaders.size(): 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name ;
    TextView hours;
    TextView country;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.skill_image);
        name = itemView.findViewById(R.id.skill_name);
        hours = itemView.findViewById(R.id.skill_score);
        country = itemView.findViewById(R.id.skill_country);
    }

        public void bind (LearningLeader leader){
            name.setText(leader.name);
//            String authors ="";
//            int i = 0;
//            for (String author:book.authors){
//                authors+=author;
//                i++;
//                if(i<book.authors.length){
//                    authors+=" , ";
//                }
//
//            }
            hours.setText(String.valueOf(leader.hours));
            country.setText(leader.country);

        }
}
}
